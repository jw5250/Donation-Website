package com.ufund.api.ufundapi.DonationRewardTests;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufund.api.ufundapi.model.DonationReward;

/**
 * Test class for DonationReward.java file.
 * 
 * @author Daniel Arcega
 */
@Tag("Model-tier")
public class DonationRewardTest {
    DonationReward testDonationReward;
    
    /**
     * Before each test, create dummy DonationReward to run tests on
     */
    @BeforeEach
    public void setupDonationReward() {
        testDonationReward = new DonationReward("Art", 10000, 5);
    }

    /**
     * Test function for "getName()" function
     * Asserts that the name of the test DonationReward is correctly returned
     */
    @Test
    public void testGetName(){
        String testName = testDonationReward.getName();
        assertEquals("Art", testName, "testGetName");
    }

    /**
     * Test function for "setName()" function
     * Asserts that the test DonationReward has its name correctly changed and stored
     */
    @Test
    public void testSetName(){
        String testName = "Sculpture";
        testDonationReward.setName(testName);
        assertEquals(testName, testDonationReward.getName(), "testSetName");
    }

    /**
     * Test function for DonationReward's "toString()" function
     * Asserts that the test DonationReward's proper string formation is returned
     */
    @Test
    public void testToString(){
        String testString = "DonationReward[name=Art, requirement=10000, quantity=5]";
        String testResult = testDonationReward.toString();
        assertEquals(testString,testResult, "testToString");
    }
}
