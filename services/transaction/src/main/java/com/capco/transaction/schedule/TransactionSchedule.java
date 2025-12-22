package com.capco.transaction.schedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.capco.transaction.model.entity.Transaction;
import com.capco.transaction.model.entity.Transaction.TransactionStatus;
import com.capco.transaction.repo.TransactionRepository;
import com.capco.transaction.service.TransactionProcessorService;
import com.capco.transaction.service.TransactionService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class TransactionSchedule {
    
    private final TransactionRepository transactionRepository;
    private final TransactionService transactionService;
    private final TransactionProcessorService transactionProcessorService;
    
    @Scheduled(fixedRateString = "${transaction.processing.interval:2000}")
    public void processTransactions() {
        log.info("Starting scheduled transaction processing");
      
        List<Transaction> pendingTransactions = transactionRepository.findByStatusIn(List.of(
            TransactionStatus.PENDING,
            TransactionStatus.PROCESSING
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
    }

}
