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
import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.persistence.EventFileDAO;

public class EventTest {
    Event testEvent;
    
    /**
     * Before each test, create dummy need to run tests on
     */
    @BeforeEach
    public void setupEvent() {
        testEvent = new Event("Art", "Jan 1", "10:00AM");
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
     * Test function for "getDate()" function
     * Asserts that the name of the test Event is correctly returned
     */
    @Test
    public void testGetDate(){
        String testName = testEvent.getDate();
        assertEquals("Jan 1", testName, "testGetDate");
    }

    /**
     * Test function for "getTime()" function
     * Asserts that the name of the test Event is correctly returned
     */
    @Test
    public void testGetTime(){
        String testName = testEvent.getTime();
        assertEquals("10:00AM", testName, "testGetTime");
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
     * Test function for "setDate()" function
     * Asserts that the test Event has its name correctly changed and stored
     */
    @Test
    public void testSetDate(){
        String testName = "Nov";
        testEvent.setDate(testName);
        assertEquals(testName, testEvent.getDate(), "testSetDate");
    }

    /**
     * Test function for "setTime()" function
     * Asserts that the test Event has its name correctly changed and stored
     */
    @Test
    public void testSetTime(){
        String testName = "9:00AM";
        testEvent.setTime(testName);
        assertEquals(testName, testEvent.getTime(), "testSetTime");
    }

    /**
     * Test function for Event's "toString()" function
     * Asserts that the test Event's proper string formation is returned
     */
    @Test
    public void testToString(){
        String testString = "Event[name=Art, date=Jan 1, time=10:00AM]";
        String testResult = testEvent.toString();
        assertEquals(testString,testResult, "testToString");
    }

    /**
     * Test function for Event's "equals()" function
     * Asserts that the test Event's proper string formation is returned
     */
    @Test
    public void testEquals(){
        Object testString = new Event("Art", "Jan 1", "10:00AM");
        boolean testResult = testEvent.equals(testString);
        assertEquals(true,testResult, "testEquals");
        Object testString2 = new Event("no", "Who 2", "IDK");
        boolean testResult2 = testEvent.equals(testString2);
        assertEquals(false,testResult2, "testEquals");
         Object testString3 = new Need("Art", "funding", 10000, 5);
        boolean testResult3 = testEvent.equals(testString3);
        assertEquals(false,testResult3, "testEquals");
        Object testString4 = new Event("Art", "Jan 1", "IDK");
        boolean testResult4 = testEvent.equals(testString4);
        assertEquals(false,testResult4, "testEquals");
        Object testString5 = new Event("Art", "Who 2", "IDK");
        boolean testResult5 = testEvent.equals(testString5);
        assertEquals(false,testResult5, "testEquals");
    }
}
