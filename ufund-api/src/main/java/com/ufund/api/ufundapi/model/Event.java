package com.ufund.api.ufundapi.model;

//import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Event {
    //private static final Logger LOG = Logger.getLogger(Need.class.getName());

    // Package private for tests
    static final String STRING_FORMAT = "Event[name=%s, date=%s, time=%d]";
     
    @JsonProperty("name") private String name;//Unique name
    @JsonProperty("date") private String date;
    @JsonProperty("time") private int time;


    /**
     * Create a need with the given attributes
     * @param name The name of Event
     * @param date The datw of Event
     * @param time The time of Event
     * {@literal @}JsonProperty is used in serialization and deserialization
     * of the JSON object to the Java object in mapping the fields.  If a field
     * is not provided in the JSON object, the Java field gets the default Java
     * value, i.e. 0 for int
     */
    public Event(@JsonProperty("name") String name, @JsonProperty("date") String date,
    @JsonProperty("time") int time) {
        this.name = name;
        this.date = date;
        this.time = time;
    }
    /*
     * In case two Event to be compared.
     */
    public boolean equals(Object T){
        if(T instanceof Event){
            Event n = (Event)T;
            if(n.name.equals(name) && n.date.equals(date) && n.time == time){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
    /**
     * Retrieves the name of the Event
     * @return The name of the Event
     */
    public String getName() {return name;}

    /**
     * Sets the name of the Event - necessary for JSON object to Java object deserialization
     * @param name The name of the Event
     */
    public void setName(String name) {this.name = name;}

    /**
     * Retrieves the date of the Event
     * @return The date of the Event
     */
    public String getDate() {return date;}

    /**
     * Sets the date of the Event - necessary for JSON object to Java object deserialization
     * @param date The date of the Event
     */
    public void setDate(String date) {this.date = date;}

    /**
     * Retrieves the time of the Event
     * @return The time of the Event
     */
    public int getTime() {return time;}

    /**
     * Sets the time of the Event - necessary for JSON object to Java object deserialization
     * @param time The time of the Need
     */
    public void setTime(int time) {this.time = time;}

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT, name, date, time);
    }

}
