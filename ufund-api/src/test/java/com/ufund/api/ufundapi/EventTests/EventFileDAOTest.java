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

public class EventFileDAOTest {
    EventFileDAO eventFileDao;
    Event[] testEvents;
    ObjectMapper mockObjectMapper;

    /**
     * Before running each test function, create a dummy Event array
     * to act as a source for the CuT to work on
     * 
     * @throws IOException
     */
    @BeforeEach
    public void setupEventFileDAO() throws IOException {
        mockObjectMapper = mock(ObjectMapper.class);
        testEvents = new Event[3];
        testEvents[0] = new Event("Art", "dec", "900");
        testEvents[1] = new Event("Teachers", "nov", "1000");
        testEvents[2] = new Event("null", "null", "0");

        when(mockObjectMapper
            .readValue(new File("none.txt"),Event[].class))
                .thenReturn(testEvents);
        //Throws a nullpointer exception because it's assumes some file always exists.
        eventFileDao = new EventFileDAO("none.txt", mockObjectMapper);
    }   

    /**
     * Tests "testGetEventArray()" function
     * Asserts that the function returns the correct objects in the correct order.
     * 
     * @throws IOException
     */
    @Test
    public void testGetEventArray() throws IOException{
        Event[] testArray = new Event[3];
        testArray[0] = new Event("Art", "dec", "900");
        testArray[1] = new Event("Teachers", "nov", "1000");
        testArray[2] = new Event("null", "null", "0");



        Event[] testResult = eventFileDao.getDataArray();
        //assertEquals(testEvents[0], testResult[0], "testGetEvent");
        //assertEquals(testEvents[1], testResult[1], "testGetEvent");
        //assertEquals(testEvents[2], testResult[2], "testGetEvent");
        assertArrayEquals(testArray, testResult, "testGetEventArray");
    }

    /**
     * Tests "testSearchEvent(String containsString)" function
     * Asserts that the function returns the correct Events that match the parameter
     * 
     * @throws IOException
     */
    @Test
    public void testSearchEvent() throws IOException{
        Event[] testArray = new Event[2];
        testArray[0] = new Event("Art", "dec", "900");
        testArray[1] = new Event("Teachers", "nov", "1000");

        Event[] testResult = eventFileDao.searchDataArray("r");
        assertArrayEquals(testArray, testResult, "testSearchEvent");
    }

    /**
     * Tests "getEvent()" function
     * Asserts that the function returns the correct object
     * 
     * @throws IOException
     */
    @Test
    public void testGetEvent() throws IOException{
        Event testEvent = new Event("Art", "dec", "900");
        Event testResult = eventFileDao.getData("Art");

        assertEquals(testEvent,testResult, "testGetEvent");
    }
    
    /**
     * Tests "deleteEvent()" function
     * Asserts that the named Event is deleted and the resulting Event array does not
     *  contain it anymore.
     * 
     * @throws IOException
     */
    @Test
    public void testDeleteEvent() throws IOException{
        Event[] testArray = new Event[2];
        testArray[0] = new Event("Art", "dec", "900");
        testArray[1] = new Event("Teachers", "nov", "1000");

        assertTrue(eventFileDao.deleteData("null"),"testDeleteEvent");

        Event[] testResult = eventFileDao.getDataArray(); 
        assertArrayEquals(testArray, testResult, "testDeleteEvent");
    }

    /**
     * Tests "createEvent()" function
     * Asserts that the created Event is properly stored in the array of Events.
     * 
     * @throws IOException
     */
    @Test
    public void testCreateEvent() throws IOException{
        Event testEvent = new Event("Garden", "no", "400");
        Event testResult = eventFileDao.createData(testEvent);
        testResult = eventFileDao.getData("Garden");
        assertNotNull(testResult, "testCreateEvent: null result");
        assertEquals(testEvent,testResult, "testCreateEvent: not matching");
    }

    /**
     * Tests "updateEvent()" function
     * Asserts that the given need is properly updated in the Event array.
     * 
     * @throws IOException
     */
    @Test
    public void testUpdateEvent() throws IOException{
        Event testEvent = new Event("Art", "aug", "6000");
        eventFileDao.updateData(testEvent);
        Event testResult = eventFileDao.getData("Art");

        assertEquals(testEvent,testResult, "testUpdateEvent");
    }

    @Test
    public void testDeleteEventNotFound() {
        // Invoke
        boolean result = assertDoesNotThrow(() -> eventFileDao.deleteData("Unknown"),
                                                "Unexpected exception thrown");

        // Analyze
        assertEquals(result,false);
    }

    @Test
    public void testUpdateEventNotFound() {
        Event testNeed = new Event("no", "aug", "6000");
        // Invoke
        Event result = assertDoesNotThrow(() -> eventFileDao.updateData(testNeed),
                                                "Unexpected exception thrown");

        // Analyze
        assertNull(result);
    }
}
