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
import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.model.User;
import com.ufund.api.ufundapi.persistence.UserFileDAO;

/**
 * Test class for UserFileDAO.java.
 * 
 * @author Justin Wu
 */
@Tag("Persistence-tier")
public class UserFileDAOTest {
    UserFileDAO UserFileDAO;
    User[] testUsers;
    ObjectMapper mockObjectMapper;

    /**
     * Before running each test function, create a dummy User array
     * to act as a source for the CuT to work on
     * 
     * @throws IOException
     */
    @BeforeEach
    public void setupUserFileDAO() throws IOException {
        mockObjectMapper = mock(ObjectMapper.class);
        Need[] arr = new Need[2];
        arr[0] = new Need("Thing1", "Type", 10, 4);
        arr[1] = new Need("Thing2", "Type2", 10, 5);

        testUsers = new User[3];

        testUsers[0] = new User("Bob Bone", false, arr, 0.0, null);
        testUsers[1] = new User("Bob Cone", true, null, 0.0, null);
        testUsers[2] = new User("Bob Stone", true, null, 0.0, null);
        when(mockObjectMapper
            .readValue(new File("none.txt"),User[].class))
                .thenReturn(testUsers);
        UserFileDAO = new UserFileDAO("none.txt", mockObjectMapper);
    }

    /**
     * Tests "getCupboardArray()" function
     * Asserts that the function returns the correct objects in the correct order.
     * 
     * @throws IOException
     */
     //Does not work.
    @Test
    public void testGetUserArray() throws IOException{
        
        User[] testResult = UserFileDAO.getDataArray();
        assertEquals(testUsers[0].getName(), testResult[0].getName());
    }

    /**
     * Tests "getCupboardArray(String containsString)" function
     * Asserts that the function returns the correct Users that match the parameter
     * 
     * @throws IOException
     */
     //Does not work.
    @Test
    public void testSearchCupboard() throws IOException{
        User testArray = new User("Bob Stone", true, null, 0.0, null);

        User testResult = UserFileDAO.searchDataArray("St")[0];

        assertEquals(testArray, testResult, "testSearchCupboardArray");
    }

    /**
     * Tests "getUser()" function
     * Asserts that the function returns the correct object
     * 
     * @throws IOException
     */
    @Test
    public void testGetUser() throws IOException{
        User testUser = new User("Bob Stone", true, null, 0.0, null);
        User testResult = UserFileDAO.getData(testUser.getName());

        assertEquals(testUser,testResult, "testGetUser");
    }
    
    /**
     * Tests "deleteUser()" function
     * Asserts that the named User is deleted and the resulting User array does not
     *  contain it anymore.
     * 
     * @throws IOException
     */
     //Does not work.
    @Test
    public void testDeleteUser() throws IOException{
        Need[] arr = new Need[2];
        arr[0] = new Need("Thing1", "Type", 10, 4);
        arr[1] = new Need("Thing2", "Type2", 10, 5);
        testUsers[0] = new User("Bob Bone", false, arr, 0.0, null);
        testUsers[1] = new User("Bob Stone", true, null, 0.0, null);
        assertTrue(UserFileDAO.deleteData("Bob Cone"),"testDeleteUser");

        User[] testResult = UserFileDAO.getDataArray(); 
        assertTrue(testUsers[0].equals(testResult[0]));
        assertTrue(testUsers[1].equals(testResult[1]));
        //assertArrayEquals(testArray, testResult, "testDeleteUser");
    }

    /**
     * Tests "createUser()" function
     * Asserts that the created User is properly stored in the array of Users.
     * 
     * @throws IOException
     */
    @Test
    public void testCreateUser() throws IOException{
        User testUser = new User("Sob Btone", false, null, 0.0, null);
        User testResult = UserFileDAO.createData(testUser);
        testResult = UserFileDAO.getData("Sob Btone");
        assertNotNull(testResult, "testCreateUser: null result");
        assertEquals(testUser,testResult, "testCreateUser: not matching");
    }

    /**
     * Tests "updateUser()" function
     * Asserts that the given User is properly updated in the User array.
     * 
     * @throws IOException
     */
    @Test
    public void testUpdateUser() throws IOException{
        User testUser = new User("Bob Stone", false, null, 0.0, null);
        UserFileDAO.updateData(testUser);
        User testResult = UserFileDAO.getData(testUser.getName());

        assertEquals(testUser,testResult, "testUpdateUser");
    }

}
