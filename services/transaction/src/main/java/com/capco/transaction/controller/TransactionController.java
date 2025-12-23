package com.capco.transaction.controller;

import org.springframework.web.bind.annotation.RestController;

import com.capco.transaction.model.entity.Transaction;
import com.capco.transaction.model.submit.TransactionRequest;
import com.capco.transaction.service.TransactionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
@RequiredArgsConstructor
@Slf4j
public class TransactionController {
    
    @Autowired
    private TransactionService transactionService;

    @RequestMapping(path = "/transaction", method=RequestMethod.GET)
    public Optional<Transaction> getTransaction() {
        Optional<Transaction>  result= transactionService.getAllPending();
        return result;
    }

    @RequestMapping(path = "/transaction", method=RequestMethod.POST)
    public ResponseEntity<String> processTransaction(@RequestBody TransactionRequest request) {
        log.info("Received transaction submission: {}", request.getTransactionId());
        String response = transactionService.submitTransaction(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
}
