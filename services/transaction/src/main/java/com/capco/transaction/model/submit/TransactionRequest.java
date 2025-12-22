package com.capco.transaction.model.submit;

import java.math.BigDecimal;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class TransactionRequest {
    private String transactionId;
    private BigDecimal amount;
    private String currency;
    private String payee;
    private String timestamp;
}
