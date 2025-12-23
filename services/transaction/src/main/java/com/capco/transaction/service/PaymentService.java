package com.capco.transaction.service;

import com.capco.transaction.model.Response.SubmissionResponse;
import com.capco.transaction.model.submit.SubmissionRequest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class PaymentService {

    public CompletableFuture<SubmissionResponse> paymentSubmit(RestClient restClient, SubmissionRequest request, TransactionProcessorService t) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                SubmissionResponse result = restClient.post()
                        .uri("/payments/submit")
                        .body(request)
                        .retrieve()
                        .onStatus(status -> status.value() >= 400, (req, res) -> {
                            throw new RuntimeException("Payment gateway returned status: " + res.getStatusCode());
                        })
                        .body(SubmissionResponse.class);

         
                if (result != null && "success".equals(result.getStatus())) {
                    boolean temp = t.markStatusValueById(request.getTransactionId(), "PROCESSED", "");
                    log.info("paymentSubmit: ID {}, Status: PROCESSED, Success: {}", request.getTransactionId(), temp);
                } else {
                    boolean temp = t.markStatusValueById(request.getTransactionId(), "FAILED", "");
                    log.error("paymentSubmit: ID {}, Status: FAILED, Success: {}", request.getTransactionId(), temp);
                }

                return result;

            } catch (Exception e) {
                log.error("Error during payment submission for ID: " + request.getTransactionId(), e);
                t.markStatusValueById(request.getTransactionId(), "FAILED", e.getMessage());
                return null; 
            }
        });
    }
}
