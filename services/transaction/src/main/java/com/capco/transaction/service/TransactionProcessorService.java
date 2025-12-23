package com.capco.transaction.service;


import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;


import com.capco.transaction.repo.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionProcessorService {
    
    private final TransactionRepository transactionRepository;
        
    @Transactional
    public boolean markAsProcessing(String transactionId) {
        int updated = transactionRepository.updateStatusToProcessing(transactionId);
        return updated > 0;
    }
 
}


