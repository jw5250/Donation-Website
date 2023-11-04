package com.ufund.api.ufundapi.model;


import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Represents a donation reward entity
 * 
 */
public class DonationReward{

    // Package private for tests
    static final String STRING_FORMAT = "DonationReward[name=%s, requirement=%d, quantity=%d]";


    @JsonProperty("name") private String name;//Unique name
    @JsonProperty("requirement") private int requirement;
    @JsonProperty("quantity") private int quantity;
    /**
     * Create a donation reward with the given attributes
     * @param name The name of donation reward
     * @param requirement The amount of donations required to get the reward.
     * @param quantity The quantity of donation reward
     * {@literal @}JsonProperty is used in serialization and deserialization
     * of the JSON object to the Java object in mapping the fields.  If a field
     * is not provided in the JSON object, the Java field gets the default Java
     * value, i.e. 0 for int
     */
    public DonationReward(@JsonProperty("name") String name, @JsonProperty("requirement") int requirement,
    @JsonProperty("quantity") int quantity
    ) {
        this.name = name;
        this.requirement = requirement;
        this.quantity = quantity;
    }
    /*
     * In case two donation rewards donation reward to be compared.
     */
    public boolean equals(Object T){
        if(T instanceof DonationReward){
            DonationReward n = (DonationReward)T;
            if(n.name.equals(name) && n.requirement == requirement && n.quantity == quantity){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    /**
     * Retrieves the name of the donation reward
     * @return The name of the donation reward
     */
    public String getName() {return name;}

    /**
     * Sets the name of the donation reward - necessary for JSON object to Java object deserialization
     * @param name The name of the donation reward
     */
    public void setName(String name) {this.name = name;}

    /**
     * Retrieves the requirement of the donation reward
     * @return The requirement of the donation reward
     */
    public int getRequirement() {return requirement;}

    /**
     * Sets the requirement of the donation reward - necessary for JSON object to Java object deserialization
     * @param requirement The name of the donation reward
     */
    public void setRequirement(int req) {this.requirement = req;}

    /**
     * Retrieves the quantity of the donation reward
     * @return The quantity of the donation reward
     */
    public int getQuantity() {return quantity;}

    /**
     * Sets the quantity of the donation reward - necessary for JSON object to Java object deserialization
     * @param quantity The quantity of the donation reward
     */
    public void setQuantity(int quantity) {this.quantity = quantity;}

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT, name, requirement, quantity);
    }
}