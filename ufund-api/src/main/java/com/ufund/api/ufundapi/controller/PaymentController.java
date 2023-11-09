/* 
package com.ufund.api.ufundapi.controller;

import com.ufund.api.ufundapi.model.paymentService;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



public class PaymentController {
    private final paymentService paymentService;
    private final Logger LOG;

    public PaymentController(paymentService paymentService) {
        this.paymentService = paymentService;
        this.LOG = Logger.getLogger(PaymentController.class.getName());
        this.LOG.info("Initialized PaymentController with paymentService");
    }

    public boolean processPayment(String userId, String cardNumber, double amount) {
        // Logic for processing the payment
        return paymentService.processTransaction(userId, cardNumber, amount);
    }

    public boolean authorizeUser(String username, String password) {
        // Logic for authorizing the user
        return paymentService.authorizeUser(username, password);
    }

    /* 
    @GetMapping("/{source}")
    public ResponseEntity<paymentService> getData(@PathVariable String source) {
        // Logic for getting data from the payment service
        this.Log.info("GET /payment/" + source);
        try {
            if(paymentService != null){
                return new ResponseEntity<paymentService>(paymentService,HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    public String getPaymentStatus(paymentService mockPaymentService) {
        // Logic for getting the payment status
        return null; // Placeholder for actual logic
    }

    public boolean paymentRefund(paymentService mockPaymentService, double refundAmount) {
        // Logic for refunding a payment
        return false; // Placeholder for actual logic
    }

    public boolean paymentCancellation(paymentService mockPaymentService) {
        // Logic for canceling a payment
        return false; // Placeholder for actual logic
    }

    public boolean paymentVerification(paymentService mockPaymentService) {
        // Logic for verifying a payment
        return false; // Placeholder for actual logic
    }
}
*/