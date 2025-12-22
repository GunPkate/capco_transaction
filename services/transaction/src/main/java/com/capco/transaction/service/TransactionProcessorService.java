package com.capco.transaction.service;


import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;


import com.capco.transaction.model.entity.Transaction.TransactionStatus;

import com.capco.transaction.repo.TransactionRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionProcessorService {
    
    private final TransactionRepository transactionRepository;
        
    @Transactional
    public boolean markAsProcessing(String transactionId) {
        int updated = transactionRepository.updateStatusToProcessing(transactionId);
        return updated > 0;
    }
 
}


