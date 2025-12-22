package com.capco.transaction.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "transactions", indexes = {
    @Index(name = "idx_status", columnList = "status"),
    @Index(name = "idx_transaction_id", columnList = "transactionId", unique = true)
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    
    @Id
    private String id;
    
    @Column(nullable = false, unique = true)
    private String transactionId;
    
    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;
    
    @Column(nullable = false, length = 3)
    private String currency;
    
    @Column(nullable = false)
    private String payee;
    
    @Column(nullable = false)
    private String timestamp;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus status;
   
      
    @Column(nullable = false, updatable = false)
    private String createdAt;
    
    @Column
    private String updatedAt;
        
    public enum TransactionStatus {
        PENDING,
        PROCESSING,
        PROCESSED,
        FAILED
    }
}
