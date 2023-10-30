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
import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.persistence.NeedFileDAO;

/**
 * Test class for NeedFileDAO.java.
 * 
 * @author Daniel Arcega
 */
@Tag("Persistence-tier")
public class NeedFileDAOTest {
    NeedFileDAO needFileDAO;
    Need[] testNeeds;
    ObjectMapper mockObjectMapper;

    /**
     * Before running each test function, create a dummy Need array
     * to act as a source for the CuT to work on
     * 
     * @throws IOException
     */
    @BeforeEach
    public void setupNeedFileDAO() throws IOException {
        mockObjectMapper = mock(ObjectMapper.class);
        testNeeds = new Need[3];
        testNeeds[0] = new Need("Art", "funding", 10000, 5);
        testNeeds[1] = new Need("Teachers", "volunteer", 0, 10);
        testNeeds[2] = new Need("null", "null", 0, 0);

        when(mockObjectMapper
            .readValue(new File("none.txt"),Need[].class))
                .thenReturn(testNeeds);
        //Throws a nullpointer exception because it's assumes some file always exists.
        needFileDAO = new NeedFileDAO("none.txt", mockObjectMapper);
    }   

    /**
     * Tests "getCupboardArray()" function
     * Asserts that the function returns the correct objects in the correct order.
     * 
     * @throws IOException
     */
    @Test
    public void testGetCupboardArray() throws IOException{
        Need[] testArray = new Need[3];
        testArray[0] = new Need("Art", "funding", 10000, 5);
        testArray[1] = new Need("Teachers", "volunteer", 0, 10);
        testArray[2] = new Need("null", "null", 0, 0);

        Need[] testResult = needFileDAO.getDataArray();

        assertArrayEquals(testArray, testResult, "testGetCupboardArray");
    }

    /**
     * Tests "getCupboardArray(String containsString)" function
     * Asserts that the function returns the correct Needs that match the parameter
     * 
     * @throws IOException
     */
    @Test
    public void testSearchCupboard() throws IOException{
        Need[] testArray = new Need[2];
        testArray[0] = new Need("Art", "funding", 10000, 5);
        testArray[1] = new Need("Teachers", "volunteer", 0, 10);

        Need[] testResult = needFileDAO.searchDataArray("r");

        assertArrayEquals(testArray, testResult, "testSearchCupboardArray");
    }

    /**
     * Tests "getNeed()" function
     * Asserts that the function returns the correct object
     * 
     * @throws IOException
     */
    @Test
    public void testGetNeed() throws IOException{
        Need testNeed = new Need("Art", "funding", 10000, 5);
        Need testResult = needFileDAO.getData("Art");

        assertEquals(testNeed,testResult, "testGetNeed");
    }
    
    /**
     * Tests "deleteNeed()" function
     * Asserts that the named Need is deleted and the resulting Need array does not
     *  contain it anymore.
     * 
     * @throws IOException
     */
    @Test
    public void testDeleteNeed() throws IOException{
        Need[] testArray = new Need[2];
        testArray[0] = new Need("Art", "funding", 10000, 5);
        testArray[1] = new Need("Teachers", "volunteer", 0, 10);

        assertTrue(needFileDAO.deleteData("null"),"testDeleteNeed");

        Need[] testResult = needFileDAO.getDataArray(); 
        assertArrayEquals(testArray, testResult, "testDeleteNeed");
    }

    /**
     * Tests "createNeed()" function
     * Asserts that the created Need is properly stored in the array of Needs.
     * 
     * @throws IOException
     */
    @Test
    public void testCreateNeed() throws IOException{
        Need testNeed = new Need("Garden", "funding", 5000, 5);
        Need testResult = needFileDAO.createData(testNeed);
        testResult = needFileDAO.getData("Garden");
        assertNotNull(testResult, "testCreateNeed: null result");
        assertEquals(testNeed,testResult, "testCreateNeed: not matching");
    }

    /**
     * Tests "updateNeed()" function
     * Asserts that the given need is properly updated in the Need array.
     * 
     * @throws IOException
     */
    @Test
    public void testUpdateNeed() throws IOException{
        Need testNeed = new Need("Art", "funding", 6000, 3);
        needFileDAO.updateData(testNeed);
        Need testResult = needFileDAO.getData("Art");

        assertEquals(testNeed,testResult, "testUpdateNeed");
    }

}
