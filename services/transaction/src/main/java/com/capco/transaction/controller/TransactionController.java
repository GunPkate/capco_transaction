package com.capco.transaction.controller;

import org.springframework.web.bind.annotation.RestController;

import com.capco.transaction.model.TransactionRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class TransactionController {
    
    @RequestMapping(path = "/transaction", method=RequestMethod.GET)
    public String getTransaction() {
        return "a";
    }

    @RequestMapping(path = "/transaction", method=RequestMethod.POST)
    public String processTransaction(@RequestBody TransactionRequest request) {
        return request.getPayee() + request.getCurrency();
    }
    
}
