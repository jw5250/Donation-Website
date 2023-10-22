package com.ufund.api.ufundapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
//The data class for the users.

public class User{
    @JsonProperty("name") private String name;
    @JsonProperty("isManager") private boolean isManager;
    public User(@JsonProperty("name") String n, @JsonProperty("isManager") boolean isMan){
        name = n;
        isManager = isMan;
    }
    /**
     * Sets the name of the need - necessary for JSON object to Java object deserialization
     * @param name The name of the need
     */
    public void setName(String name) {this.name = name;}
    //Also a getter.
    public String getName(){
        return name;
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
