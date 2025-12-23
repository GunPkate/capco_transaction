package com.capco.transaction.model.submit;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionRequest {
    private String transactionId;
    private BigDecimal amount;
    private String currency;
    private String payee;
}
