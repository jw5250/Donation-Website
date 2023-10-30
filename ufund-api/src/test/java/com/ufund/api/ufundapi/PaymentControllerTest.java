package com.ufund.api.ufundapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.stripe.exception.StripeException;
import com.ufund.api.ufundapi.controller.PaymentController;
import com.ufund.api.ufundapi.model.paymentService;
import com.ufund.api.ufundapi.model.Charge;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

/**
 * Test class for the PaymentController class.
 * Contains unit tests for the various methods within PaymentController.
 */
@Tag("Controller-tier")
public class PaymentControllerTest {
    private PaymentController paymentController;
    private paymentService mockPaymentService;

    /**
     * Sets up the PaymentController and the mock payment service before each test.
     */
    @BeforeEach
    public void setupPaymentController() {
        mockPaymentService = mock(paymentService.class);
        paymentController = new PaymentController(mockPaymentService);
    }


    /**
     * Tests the payment request method.
     * Ensures that the payment service is correctly updated upon a successful charge.
     *
     * @throws StripeException if there is an error with the Stripe API
     */
    @Test
    public void testPaymentRequest() throws StripeException {
        // Mocking the payment service
        mockPaymentService = new paymentService(2000, "usd", "tok_visa", "Test Payment", "ch_1234567890");
        when(mockPaymentService.getStatus()).thenReturn(Charge.chargeStatus.SUCCESSFUL);

        ResponseEntity<paymentService> response = paymentController.getData(mockPaymentService);

        assertEquals(Charge.chargeStatus.SUCCESSFUL, mockPaymentService.getStatus());
    }


    /**
     * Tests the method to get the payment status.
     * Checks if the correct payment status is returned based on the provided payment service.
     */
    @Test
    public void testGetPaymentStatus() {
        mockPaymentService = new paymentService(2000, "usd", "tok_visa", "Test Payment");
        when(mockPaymentService.getStatus()).thenReturn(Charge.chargeStatus.SUCCESSFUL);

        String paymentStatus = paymentController.getPaymentStatus(mockPaymentService);

        assertEquals("SUCCESSFUL", paymentStatus);
    }


    /**
     * Tests the payment refund method.
     * Verifies that the payment is successfully refunded for the given payment service and refund amount.
     *
     * @throws StripeException if there is an error with the Stripe API
     */
    @Test
    public void testPaymentRefund() throws StripeException {
        mockPaymentService = new paymentService(2000, "usd", "tok_visa", "Test Payment");
        when(mockPaymentService.refund(1000)).thenReturn(true);

        boolean isRefunded = paymentController.paymentRefund(mockPaymentService, 1000);

        assertTrue(isRefunded);
    }

    /**
     * Tests the payment cancellation method.
     * Verifies that the payment is successfully canceled for the provided payment service.
     *
     * @throws StripeException if there is an error with the Stripe API
     */
    @Test
    public void testPaymentCancellation() throws StripeException {
        mockPaymentService = new paymentService(2000, "usd", "tok_visa", "Test Payment");
        when(mockPaymentService.cancelPayment()).thenReturn(true);

        boolean isCancelled = paymentController.paymentCancellation(mockPaymentService);

        assertTrue(isCancelled);
    }


    /**
     * Tests the payment verification method.
     * Checks if the payment is successfully verified for the provided payment service.
     *
     * @throws StripeException if there is an error with the Stripe API
     */
    @Test
    public void testPaymentVerification() throws StripeException {
        mockPaymentService = new paymentService(2000, "usd", "tok_visa", "Test Payment");
        when(mockPaymentService.verifyPayment()).thenReturn(true);

        boolean isVerified = paymentController.paymentVerification(mockPaymentService);

        assertTrue(isVerified);
    }
}
