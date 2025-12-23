package com.capco.transaction.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.capco.transaction.model.Response.TransactionResponse;
import com.capco.transaction.model.entity.Transaction;
import com.capco.transaction.model.entity.Transaction.TransactionStatus;
import com.capco.transaction.model.submit.TransactionRequest;
import com.capco.transaction.repo.TransactionRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    
    @Transactional
    public String submitTransaction(TransactionRequest request) {
        String id = UUID.randomUUID().toString();
        log.info("Submitting transaction: {} uuid {}", id,  request.getTransactionId());
        
         
        Transaction transaction = Transaction.builder()
                .id(id)
                .transactionId(request.getTransactionId())
                .amount(request.getAmount())
                .currency(request.getCurrency())
                .payee(request.getPayee())
                .timestamp(request.getTimestamp())
                .createdAt(request.getTimestamp())
                .status(Transaction.TransactionStatus.PENDING)
                .build();
        
        Transaction saved = transactionRepository.save(transaction);
        log.info("Transaction saved with ID: {}", saved.getId());
        
        return "Transaction saved: "+ saved.getId();
    }

    public Optional<Transaction> getAllPending() {
        return transactionRepository.findByStatusIn(List.of(
            TransactionStatus.PENDING,
            TransactionStatus.PROCESSING
    ));
    }

    public static class DuplicateTransactionException extends RuntimeException {
        public DuplicateTransactionException(String message) {
            super(message);
        }
    }
    
    public static class TransactionNotFoundException extends RuntimeException {
        public TransactionNotFoundException(String message) {
            super(message);
        }
    }

}
