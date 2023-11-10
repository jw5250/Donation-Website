/* 
package com.ufund.api.ufundapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;



import com.stripe.exception.StripeException;
import com.ufund.api.ufundapi.controller.NeedsController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

// Payment serrvice classes
import com.ufund.api.ufundapi.controller.PaymentController;
import com.ufund.api.ufundapi.model.paymentService;
import com.stripe.model.Charge;
import com.ufund.api.ufundapi.model.Charge.chargeStatus;


public class PaymentServiceTest {
    /**
     * This function tests if the function constructor works and the payment object 
     * is correctly updated
     * The server response given a successful update
     * The server response given an unsuccessful update (nonexistent target)
     * The server response given an IOexception.
     * @throws IOException
    
    @Test
    public void testPaymentRequest() throws StripeException {
        // Mocking Stripe API
        
        Mockito.mockStatic(Charge.class);
        
        
        // Simulating payment request
        int amount = 2000;
        String currency = "usd";
        String source = "tok_visa";
        String description = "Test Payment";
        paymentService mockCharge = new paymentService(amount, currency, source, description);
        // when payment is successful then transaction should go through
        when(mockCharge.getStatus().equals(paymentService.chargeStatus.SUCCESSFUL)).thenReturn(true);

        ResponseEntity<paymentService> response = PaymentController.getData(mockCharge);

        // Assertion for a successful charge
        assertEquals(paymentService.chargeStatus.SUCCESSFUL, mockCharge.getStatus());
    }

    /**
     * Tests the method for validating a valid card number.
     * Checks if the provided card number is valid.
     
    @Test
    public void testValidCardNumber() {
        assertTrue(paymentService.isValidCardNumber("1234567890123456"));
    }

    /**
     * Tests the method for validating an invalid card number.
     * Checks if the provided card number is invalid.
     
    @Test
    public void testInvalidCardNumber() {
        assertFalse(paymentService.isValidCardNumber("12345"));
    }

    /**
     * Tests the method for validating a valid user authorization.
     * Checks if the provided username and password are valid.
     
    @Test
    public void testValidUserAuthorization() {
        assertTrue(paymentService.authorizeUser("username", "password"));
    }

    /**
     * Tests the method for validating an invalid user authorization.
     * Checks if the provided username and password are invalid.
     
    @Test
    public void testInvalidUserAuthorization() {
        assertFalse(paymentService.authorizeUser("invalid_username", "invalid_password"));
    }

    /**
     * Tests the method for processing a successful transaction.
     * Checks if the transaction is processed successfully.
     
    @Test
    public void testSuccessfulTransaction() {
        assertTrue(paymentService.processTransaction("user_id", "card_number", 100.00));
    }

    /**
     * Tests the method for processing a failed transaction.
     * Checks if the transaction fails to process with an invalid card number.
     
    @Test
    public void testFailedTransaction() {
        assertFalse(paymentService.processTransaction("user_id", "invalid_card_number", 100.00));
    }

    /**
     * Tests the method for handling an invalid request error.
     * Verifies that the correct error message is returned for an invalid request.
     
    @Test
    public void testInvalidRequestError() {
        assertEquals("Invalid request", paymentService.processTransaction(null, null, 0));
    }
}
*/