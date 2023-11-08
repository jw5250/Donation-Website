package com.ufund.api.ufundapi.model;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;
//The data class for the users.

public class User{
    @JsonProperty("name") private String name;
    @JsonProperty("isManager") private boolean isManager;
    @JsonProperty("fundingBasket") private Need[] fundingBasket;
    @JsonProperty("totalDonations") private double totalDonations;
    @JsonProperty("availableRewards") private String[] availableRewards;
    

    public User(@JsonProperty("name") String n, @JsonProperty("isManager") boolean isMan, @JsonProperty("fundingBasket") Need[] fundingBask, 
     @JsonProperty("totalDonations") double moneySpent, @JsonProperty("availableRewards") String[] availability){
        name = n;
        isManager = isMan;
        totalDonations = moneySpent;
        if(fundingBask == null){
            fundingBasket = null;
            return;
        }
        setFundingBasket(fundingBask);
        availableRewards = availability;
    }
    /**
     * Sets the name of the need - necessary for JSON object to Java object deserialization
     * @param name The name of the need
     */
    public void setName(String name) {this.name = name;}

    //Is this needed?
    public void setFundingBasket(Need[] fundingBask){
        fundingBasket = new Need[fundingBask.length];
        //Deep copy.
        for(int i = 0; i < fundingBasket.length;i++){
            fundingBasket[i] = new Need(fundingBask[i].getName(), fundingBask[i].getType(), fundingBask[i].getCost(), fundingBask[i].getQuantity());
        }
    }

    public String[] getAvailableRewards(){
        return availableRewards;
    }

    public double getTotalDonations(){
        return totalDonations;
    }
    //Also a getter.
    public String getName(){
        return name;
    }
    public Need[] getFundingBasket(){
        return fundingBasket;
    }
    //Getter. Probably User to preserve privacy of the account.
    public boolean getIsManager(){
        return isManager;
    }
    //Equals function.

    public boolean equals(Object o){
        if(o instanceof User){
            User newUser = (User)o;
            if(newUser.name.equals(name)){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}
