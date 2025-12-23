package com.capco.transaction.service;

import com.capco.transaction.model.Response.SubmissionResponse;
import com.capco.transaction.model.submit.SubmissionRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class PaymentService {   
    public SubmissionResponse paymentSubmit(RestClient restClient, SubmissionRequest request){
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
        return result;
    }
}
