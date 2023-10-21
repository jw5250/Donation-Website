package com.ufund.api.ufundapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.ufund.api.ufundapi.model.FundingBasket;
import com.ufund.api.ufundapi.model.Need;

/**
 * The unit test suite for the Hero class
 * 
 * @author Ricky Yang
 */
@Tag("Model-tier")
public class FundingBasketTest {
    @Test
    public void testCtor() {
        
        String expected_name = "thing1";
        String expected_type = "type1";
        int cost = 99;
        int quantity = 10;

        // Invoke
        Need Data = new Need(expected_name, expected_type, cost, quantity);


        // Analyze
        assertEquals(expected_name,Data.getName());
        assertEquals(expected_type,Data.getType());
        assertEquals(cost,Data.getCost());
        assertEquals(quantity,Data.getQuantity());
    }

    @Test
    public void testName() {
        // Setup
        String name = "thing1";
        String expected_type = "type1";
        int cost = 99;
        int quantity = 10;

        // Invoke
        Need Data = new Need(name, expected_type, cost, quantity);

        String expected_name = "thing2";

        // Invoke
        Data.setName(expected_name);

        // Analyze
        assertEquals(expected_name,Data.getName());
    }
}