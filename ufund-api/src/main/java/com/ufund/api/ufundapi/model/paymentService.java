/**
package com.ufund.api.ufundapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
//import com.stripe.param.FeeRefundCreateParams;
//import com.stripe.param.FeeRefundCreateParams.Builder;
import com.stripe.model.Charge;
import com.stripe.model.Refund;

public class paymentService {


    @JsonProperty("amount") private double amount;
    @JsonProperty("currency") private String currency;
    @JsonProperty("source") private String source;
    @JsonProperty("description") private String description;
    @JsonProperty("status") private chargeStatus status;
    @JsonProperty("chargeID") private String chargeID;

    private enum TransactionType {
        DONATION("Donation"),
        CHARITY("Charity"),
        GOFUNDME("GoFundMe"),
        CROWDFUNDING("Crowdfunding"),
        FUNDRAISING("Fundraising");

        private final String description;

        TransactionType(String description) {
            this.description = description;
        }

        private String getDescription() {
            return description;
        }
    }

    public enum chargeStatus {
        SUCCESSFUL,
        FAILED,
        PENDING
    }

    public paymentService(@JsonProperty("amount") int amount, @JsonProperty("currency") String currency, 
    @JsonProperty("source") String source, @JsonProperty("description") String description, 
    @JsonProperty("chargeID") String chargeID) {
        this.amount = amount;
        this.currency = currency;
        this.source = source;
        this.description = description;
        status = chargeStatus.PENDING;
    }

    public boolean setStatus(chargeStatus nStat) {
        this.status = nStat;
        // successful set status
        return nStat.equals(status);
    }

    public chargeStatus getStatus() {
        return this.status;
    }

    public boolean processTransaction(String userId, String cardNumber, double amount) {
        // Logic for processing the transaction
        return true; // Placeholder for actual logic
    }

    public boolean authorizeUser(String username, String password) {
        // Logic for authorizing the user
        return true; // Placeholder for actual logic
    }

    public boolean verifyPayment(paymentService pay){
        if ((pay.getStatus()).equals(paymentService.chargeStatus.SUCCESSFUL)){
            return true;
        } else {
            return false;
        }
    }

    public static boolean isValidCardNumber(String cardNumber) {
        // Logic to validate card number
        return true; // Placeholder for actual logic
    }
     /**
     * Refunds the specified amount for the payment service.
     *
     * @param refundAmount The amount to be refunded
     * @return True if the refund is successful, false otherwise
     * @throws StripeException if there is an error with the Stripe API
     
    public boolean refund(double refundAmount) throws StripeException {
        try {
            // Set your secret key; gotta remember to change this to a live secret key in production
            // See keys here: https://dashboard.stripe.com/account/apikeys
            Stripe.apiKey = "sk_test_your_secret_key_here";
            RefundCreateParams params = RefundCreateParams.builder()
            .setCharge(chargeID)
            .setAmount((long) (refundAmount * 100)) // Amount in cents
            .build();

            // Return true if the refund is successful
            return true;
        } catch (StripeException e) {
            // Log the error or handle it appropriately
            throw e;
        }
    }


    public String getSource(){
        return source;
    }
}
*/