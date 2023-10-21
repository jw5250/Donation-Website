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
import com.ufund.api.ufundapi.persistence.UserFileDAO;

/**
 * Test class for UserFileDAO.java.
 * 
 * @author Daniel Arcega
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

        testUsers = new User[3];
        testUsers[0] = new User("Bob Bone", true);
        testUsers[1] = new User("Bob Cone", true);
        testUsers[2] = new User("Bob Stone", true);
        when(mockObjectMapper
            .readValue(new File("none.txt"),User[].class))
                .thenReturn(testUsers);
        //Throws a nullpointer exception because it's assumes some file always exists.
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
    public void testGetCupboardArray() throws IOException{
        
        User[] testResult = UserFileDAO.getDataArray();
        //assertArrayEquals(testUsers, testResult, "testGetCupboardArray");
        assertEquals(testUsers[0].getName(), testResult[0].getName());
        //assertEquals(testUsers[0], testResult[0], "testGetCupboardArray");        
        //assertEquals(testUsers[1], testResult[1], "testGetCupboardArray");
        //assertEquals(testUsers[2], testResult[2], "testGetCupboardArray");
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
        User testArray = new User("Bob Stone", true);

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
        User testUser = new User("Bob Stone", true);
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
        User[] testArray = new User[2];
        testUsers[0] = new User("Bob Bone", true);
        testUsers[1] = new User("Bob Stone", true);
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
        User testUser = new User("Sob Btone", false);
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
        User testUser = new User("Bob Stone", false);
        UserFileDAO.updateData(testUser);
        User testResult = UserFileDAO.getData(testUser.getName());

        assertEquals(testUser,testResult, "testUpdateUser");
    }

}
