package com.capco.transaction.service;

import com.capco.transaction.model.Response.SubmissionResponse;
import com.capco.transaction.model.submit.SubmissionRequest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@Slf4j
public class PaymentService {   
    public SubmissionResponse paymentSubmit(RestClient restClient, SubmissionRequest request, TransactionProcessorService t){
        SubmissionResponse result = restClient.post()
                    .uri("/payments/submit")
                    .body(request)
                    .retrieve()
                    .onStatus(status -> status.value() >= 400, (req, res) -> {
                        try {
                            throw new Exception(
                                "Payment gateway returned status: " + res.getStatusCode()
                            );
                        } catch (Exception e) {
  
                            e.printStackTrace();
                        }
                    })
                    .body(SubmissionResponse.class);
    
                    if(result.getStatus().equals("success")) {
                        boolean temp = t.markStatusValueById(request.getTransactionId(), "PROCESSED", "");
                        log.info("paymentSubmit: ",request.getTransactionId(), "PROCESSED", temp );
                    }
                    else {
                        boolean temp = t.markStatusValueById(request.getTransactionId(), "FAILED", "");
                        log.error("paymentSubmit: ",request.getTransactionId(), "PROCESSED", temp );
                    }
        return result;
    }
}
