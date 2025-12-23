package com.capco.transaction.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.capco.transaction.repo.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionProcessorService {
    
    private final TransactionRepository transactionRepository;
        
    @Transactional
    public boolean markAsProcessing(String transactionId) {
        int updated = transactionRepository.updateStatusValueById("PROCESSING",transactionId);
        return updated > 0;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean markStatusValueById(String transactionId, String status, String processedAt) {
        int updated = transactionRepository.updateStatusValueById(status, transactionId);
        transactionRepository.flush();
        return updated > 0;
    }
 
}


