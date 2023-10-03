package com.ufund.api.ufundapi.model;

import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Represents a Need entity
 * 
 */
public class Need {
    private static final Logger LOG = Logger.getLogger(Need.class.getName());

    // Package private for tests
    static final String STRING_FORMAT = "Need[name=%s, type=%s, cost=%d, quantity=%d]";


    @JsonProperty("name") private String name;//Unique name
    @JsonProperty("type") private String type;
    @JsonProperty("cost") private int cost;
    @JsonProperty("quantity") private int quantity;
    /**

     * Create a need with the given id and name
     * @param name The name of need
     * @param type The type of need
     * @param cost The cost of need
     * @param quantity The quantity of need
     * {@literal @}JsonProperty is used in serialization and deserialization
     * of the JSON object to the Java object in mapping the fields.  If a field
     * is not provided in the JSON object, the Java field gets the default Java
     * value, i.e. 0 for int
     */
    public Need(@JsonProperty("name") String name, @JsonProperty("type") String type,
    @JsonProperty("cost") int cost,
    @JsonProperty("quantity") int quantity
    ) {
        this.name = name;
        this.type = type;
        this.cost = cost;
        this.quantity = quantity;
    }


    /**
     * Retrieves the name of the need
     * @return The name of the need
     */
    public String getName() {return name;}

    /**
     * Sets the name of the need - necessary for JSON object to Java object deserialization
     * @param name The name of the need
     */
    public void setName(String name) {this.name = name;}

    /**
     * Retrieves the type of the need
     * @return The type of the need
     */
    public String getType() {return type;}

    /**
     * Sets the type of the need - necessary for JSON object to Java object deserialization
     * @param name The type of the need
     */
    public void setType(String type) {this.type = type;}

    /**
     * Retrieves the cost of the need
     * @return The cost of the need
     */
    public int getCost() {return cost;}

    /**
     * Sets the cost of the need - necessary for JSON object to Java object deserialization
     * @param cost The name of the hero
     */
    public void setCost(int cost) {this.cost = cost;}

    /**
     * Retrieves the quantity of the need
     * @return The quantity of the need
     */
    public int getQuantity() {return quantity;}

    /**
     * Sets the quantity of the need - necessary for JSON object to Java object deserialization
     * @param quantity The quantity of the hero
     */
    public void setQuantity(int quantity) {this.quantity = quantity;}

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT, name, type, cost, quantity);
    }
}