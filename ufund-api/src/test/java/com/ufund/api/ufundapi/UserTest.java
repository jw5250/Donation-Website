package com.ufund.api.ufundapi;

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
import com.ufund.api.ufundapi.model.User;
import com.ufund.api.ufundapi.persistence.NeedFileDAO;

/**
 * Test class for Need.java file.
 * 
 * @author Justin Wu
 */
@Tag("Model-tier")
public class UserTest {
    User testUser;
    
    /**
     * Before each test, create dummy need to run tests on
     */
    @BeforeEach
    public void setupUser() {
        testUser = new User("Art", true);
    }

    /**
     * Test function for "getName()" function
     * Asserts that the name of the test Need is correctly returned
     */
    @Test
    public void testGetName(){
        String testName = testUser.getName();
        assertEquals("Art", testName, "testGetName");
    }

    /**
     * Test function for "setName()" function
     * Asserts that the test Need has its name correctly changed and stored
     */
    @Test
    public void testSetName(){
        String testName = "Sculpture";
        testUser.setName(testName);
        assertEquals(testName, testUser.getName(), "testSetName");
    }

    @Test
    public void testGetIsManager(){
        User testUser = new User("Bob", true);
        assertEquals(testUser.getIsManager(), true);
    }
}
