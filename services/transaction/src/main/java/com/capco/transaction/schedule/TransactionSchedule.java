package com.capco.transaction.schedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.capco.transaction.model.Response.SubmissionResponse;
import com.capco.transaction.model.entity.Transaction;
import com.capco.transaction.model.entity.Transaction.TransactionStatus;
import com.capco.transaction.model.submit.SubmissionRequest;
import com.capco.transaction.repo.TransactionRepository;
import com.capco.transaction.service.PaymentService;
import com.capco.transaction.service.TransactionProcessorService;
import com.capco.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j

public class TransactionSchedule {
    
    private final TransactionRepository transactionRepository;
    private final TransactionProcessorService transactionProcessorService;
    private final RestClient restClient;
    private final PaymentService paymentService;
    
    @Scheduled(fixedRateString = "${transaction.processing.interval:2000}")
    public void processTransactions() {
        log.info("Starting scheduled transaction processing");
      
        List<Transaction> pendingTransactions = transactionRepository.findAllByStatusIn(List.of(
            TransactionStatus.PENDING,
            TransactionStatus.PROCESSING,
            TransactionStatus.FAILED
        )).stream().toList();
        
     

        if (pendingTransactions.isEmpty()) {
            log.info("No pending transactions to process");
            return;
        }
        
        log.info("Found {} pending transactions", pendingTransactions.size());
        

        List<String> transactionIds = pendingTransactions.stream()
                .filter(t -> transactionProcessorService.markAsProcessing(t.getId()))
                .map(Transaction::getId)
                .collect(Collectors.toList());
        
        log.info("Marked {} transactions as PROCESSING", transactionIds.size());


        List<CompletableFuture<SubmissionResponse>> futures = pendingTransactions.stream()
        .map(t -> {
            log.info("Starting payment processing for transaction: {}", t.getTransactionId());
            return this.paymentService.paymentSubmit( restClient,  convertData(t),  transactionProcessorService);
        })
        .toList();
 
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        List<SubmissionResponse> results = allFutures.thenApply(v -> 
        futures.stream()
            .map(CompletableFuture::join)
            .toList()
        ).join(); 

        log.info("Submission result: {} ", results.size());

        logProcessingStats();
    }

    public SubmissionRequest convertData(Transaction transaction){
        return SubmissionRequest.builder()
                .transactionId(transaction.getTransactionId())
                .amount(transaction.getAmount())
                .currency(transaction.getCurrency())
                .payee(transaction.getPayee())
                .build();
    }

    private void logProcessingStats() {
        int pending = transactionRepository.countByStatus(TransactionStatus.PENDING);
        int processing = transactionRepository.countByStatus(TransactionStatus.PROCESSING);
        int processed = transactionRepository.countByStatus(TransactionStatus.PROCESSED);
        int failed = transactionRepository.countByStatus(TransactionStatus.FAILED);
        
        log.info("Transaction stats - Pending: {}, Processing: {}, Processed: {}, Failed: {}", 
                pending, processing, processed, failed);
    }

}
