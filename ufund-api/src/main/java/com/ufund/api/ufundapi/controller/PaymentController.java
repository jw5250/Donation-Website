package com.ufund.api.ufundapi.controller;
import com.ufund.api.ufundapi.model.paymentService;

public class PaymentController {
    private final paymentService paymentService;

    public PaymentController(paymentService paymentService) {
        this.paymentService = paymentService;
    }

    public boolean processPayment(String userId, String cardNumber, double amount) {
        // Logic for processing the payment
        return paymentService.processTransaction(userId, cardNumber, amount);
    }

    public boolean authorizeUser(String username, String password) {
        // Logic for authorizing the user
        return paymentService.authorizeUser(username, password);
    }

    // Other methods related to payment handling
    // ...
}