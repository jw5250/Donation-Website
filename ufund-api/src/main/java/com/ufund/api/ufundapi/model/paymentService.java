package com.ufund.api.ufundapi.model;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;


public class paymentService{
    Charge payment;
    chargeStatus paymentStatus;

    private double amount;
    private String currency;
    private String source;
    private String description;
    private chargeStatus status;

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
    public enum chargeStatus{
        SUCCESSFUL,
        FAILED,
        PENDING
    }

    /**
     * Contructor for paymentService class
     */ 
    public paymentService(double amount, String currency, String source, String description) {
        this.amount = amount;
        this.currency = currency;
        this.source = source;
        this.description = description;
        status = chargeStatus.PENDING;
    }

    public paymentService(com.ufund.api.ufundapi.model.Charge mockCharge) {
    }

    public boolean setStatus(chargeStatus nStat){
        this.status = nStat;
        // successful set status
        if (nStat.equals(status)) return true;
        // failed to set value
        return false;
    }
    public chargeStatus getStatus(){
       return this.status;
    }

    public Charge getPayment(){
        return payment;
    }
   
   
}
