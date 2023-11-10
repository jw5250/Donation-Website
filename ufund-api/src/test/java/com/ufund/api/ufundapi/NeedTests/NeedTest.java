package com.ufund.api.ufundapi.NeedTests;

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
import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.persistence.NeedFileDAO;

/**
 * Test class for Need.java file.
 * 
 * @author Daniel Arcega
 */
@Tag("Model-tier")
public class NeedTest {
    Need testNeed;
    
    /**
     * Before each test, create dummy need to run tests on
     */
    @BeforeEach
    public void setupNeed() {
        testNeed = new Need("Art", "funding", 10000, 5);
    }

    /**
     * Test function for "getName()" function
     * Asserts that the name of the test Need is correctly returned
     */
    @Test
    public void testGetName(){
        String testName = testNeed.getName();
        assertEquals("Art", testName, "testGetName");
    }

    /**
     * Test function for "setName()" function
     * Asserts that the test Need has its name correctly changed and stored
     */
    @Test
    public void testSetName(){
        String testName = "Sculpture";
        testNeed.setName(testName);
        assertEquals(testName, testNeed.getName(), "testSetName");
    }

    /**
     * Test function for Need's "toString()" function
     * Asserts that the test Need's proper string formation is returned
     */
    @Test
    public void testToString(){
        String testString = "Need[name=Art, type=funding, cost=10000, quantity=5]";
        String testResult = testNeed.toString();
        assertEquals(testString,testResult, "testToString");
    }
}
