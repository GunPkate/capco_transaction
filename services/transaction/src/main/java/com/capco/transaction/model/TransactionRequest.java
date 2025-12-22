package com.capco.transaction.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TransactionRequest {
    private String transactionId;
    private BigDecimal amount;
    private String currency;
    private String payee;
}
