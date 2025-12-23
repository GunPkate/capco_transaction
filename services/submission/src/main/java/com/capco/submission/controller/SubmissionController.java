package com.capco.submission.controller;

import org.springframework.web.bind.annotation.RestController;

import com.capco.submission.model.Submission;
import com.capco.submission.model.SubmissionRequest;
// import com.capco.submission.service.SubmissionService;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
@Slf4j
public class SubmissionController {
    
    // @Autowired
    // private SubmissionService submissionService;

    @RequestMapping(path = "/payments/submit", method=RequestMethod.POST)
    public Optional<Submission> getsubmission(@RequestBody SubmissionRequest request) {
        
        log.info("Payment: {}", request.getTransactionId() );
        // Optional<Submission>  result= submissionService.getAllPending();
        return null;
    }
    
}
