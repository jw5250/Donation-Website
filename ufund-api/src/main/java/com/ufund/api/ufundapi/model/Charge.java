package com.ufund.api.ufundapi.model;

public class Charge {
    private double amount;
    private String currency;
    private String source;
    private String description;
    private chargeStatus status;


    public enum chargeStatus{
        SUCCESSFUL,
        FAILED,
        PENDING
    }


    public Charge(double amount, String currency, String source, String description) {
        this.amount = amount;
        this.currency = currency;
        this.source = source;
        this.description = description;
        status = chargeStatus.PENDING;
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
}
