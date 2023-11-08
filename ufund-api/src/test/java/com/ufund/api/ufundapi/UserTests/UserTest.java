package com.ufund.api.ufundapi.UserTests;

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
import com.ufund.api.ufundapi.model.Need;

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
        Need[] arr = new Need[2];
        arr[0] = new Need("Thing1", "Type", 10, 4);
        arr[1] = new Need("Thing2", "Type2", 10, 5);
        testUser = new User("Art", true, arr, 0.0, null);
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

    /**
     * Test function for "equals()" function
     * Test if two users have equal names.
     */
    @Test
    public void testEquals(){
        Need[] test = new Need[1];
        test[0] = new Need("a", "b", 0, 1);
        User testUser1 = new User("Bob", true, null, 0.0, null);
        User testUser2 = new User("Bob", false, test, 0.0, null);
        assertEquals(testUser1, testUser2, "testSetName");
    }

    @Test
    public void testGetIsManager(){
        User testUser = new User("Bob", true, null, 0.0, null);
        assertEquals(testUser.getIsManager(), true);
    }
}
