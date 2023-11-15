package com.ufund.api.ufundapi.EventTests;

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
import com.ufund.api.ufundapi.model.Event;
import com.ufund.api.ufundapi.persistence.EventFileDAO;

public class EventTest {
    Event testEvent;
    
    /**
     * Before each test, create dummy need to run tests on
     */
    @BeforeEach
    public void setupEvent() {
        testEvent = new Event("Art", "Jan 1", 1000);
    }

    /**
     * Test function for "getName()" function
     * Asserts that the name of the test Event is correctly returned
     */
    @Test
    public void testGetName(){
        String testName = testEvent.getName();
        assertEquals("Art", testName, "testGetName");
    }

    /**
     * Test function for "setName()" function
     * Asserts that the test Event has its name correctly changed and stored
     */
    @Test
    public void testSetName(){
        String testName = "Sculpture";
        testEvent.setName(testName);
        assertEquals(testName, testEvent.getName(), "testSetName");
    }

    /**
     * Test function for Event's "toString()" function
     * Asserts that the test Event's proper string formation is returned
     */
    @Test
    public void testToString(){
        String testString = "Event[name=Art, date=Jan 1, time=1000]";
        String testResult = testEvent.toString();
        assertEquals(testString,testResult, "testToString");
    }
}
