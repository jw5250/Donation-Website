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
import com.ufund.api.ufundapi.persistence.DonationRewardFileDAO;

/**
 * Test class for DonationRewardFileDAO.java.
 * 
 * @author Daniel Arcega
 */
@Tag("Persistence-tier")
public class DonationRewardFileDAOTest {
    DonationRewardFileDAO DonationRewardFileDAO;
    DonationReward[] testDonationRewards;
    ObjectMapper mockObjectMapper;

    /**
     * Before running each test function, create a dummy DonationReward array
     * to act as a source for the CuT to work on
     * 
     * @throws IOException
     */
    @BeforeEach
    public void setupDonationRewardFileDAO() throws IOException {
        mockObjectMapper = mock(ObjectMapper.class);
        testDonationRewards = new DonationReward[3];
        testDonationRewards[0] = new DonationReward("Art", 10000);
        testDonationRewards[1] = new DonationReward("Teachers", 0);
        testDonationRewards[2] = new DonationReward("null", 0);

        when(mockObjectMapper
            .readValue(new File("none.txt"),DonationReward[].class))
                .thenReturn(testDonationRewards);
        //Throws a nullpointer exception because it's assumes some file always exists.
        DonationRewardFileDAO = new DonationRewardFileDAO("none.txt", mockObjectMapper);
    }   

    /**
     * Tests "getDonationRewardArray()" function
     * Asserts that the function returns the correct objects in the correct order.
     * 
     * @throws IOException
     */
    @Test
    public void testGetDonationRewardArray() throws IOException{
        DonationReward[] testArray = new DonationReward[3];
        testArray[0] = new DonationReward("Art", 10000);
        testArray[1] = new DonationReward("Teachers", 0);
        testArray[2] = new DonationReward("null", 0);

        DonationReward[] testResult = DonationRewardFileDAO.getDataArray();

        assertArrayEquals(testArray, testResult, "testGetDonationRewardArray");
    }

    /**
     * Tests "getDonationRewardArray(String containsString)" function
     * Asserts that the function returns the correct DonationRewards that match the parameter
     * 
     * @throws IOException
     */
    @Test
    public void testSearchDonationReward() throws IOException{
        DonationReward[] testArray = new DonationReward[2];
        testArray[0] = new DonationReward("Art", 10000);
        testArray[1] = new DonationReward("Teachers", 0);

        DonationReward[] testResult = DonationRewardFileDAO.searchDataArray("r");

        assertArrayEquals(testArray, testResult, "testSearchDonationRewardArray");
    }

    /**
     * Tests "getDonationReward()" function
     * Asserts that the function returns the correct object
     * 
     * @throws IOException
     */
    @Test
    public void testGetDonationReward() throws IOException{
        DonationReward testDonationReward = new DonationReward("Art", 10000);
        DonationReward testResult = DonationRewardFileDAO.getData("Art");

        assertEquals(testDonationReward,testResult, "testGetDonationReward");
    }
    
    /**
     * Tests "deleteDonationReward()" function
     * Asserts that the named DonationReward is deleted and the resulting DonationReward array does not
     *  contain it anymore.
     * 
     * @throws IOException
     */
    @Test
    public void testDeleteDonationReward() throws IOException{
        DonationReward[] testArray = new DonationReward[2];
        testArray[0] = new DonationReward("Art", 10000);
        testArray[1] = new DonationReward("Teachers", 0);

        assertTrue(DonationRewardFileDAO.deleteData("null"),"testDeleteDonationReward");

        DonationReward[] testResult = DonationRewardFileDAO.getDataArray(); 
        assertArrayEquals(testArray, testResult, "testDeleteDonationReward");
    }

    /**
     * Tests "createDonationReward()" function
     * Asserts that the created DonationReward is properly stored in the array of DonationRewards.
     * 
     * @throws IOException
     */
    @Test
    public void testCreateDonationReward() throws IOException{
        DonationReward testDonationReward = new DonationReward("Garden", 5000);
        DonationReward testResult = DonationRewardFileDAO.createData(testDonationReward);
        testResult = DonationRewardFileDAO.getData("Garden");
        assertNotNull(testResult, "testCreateDonationReward: null result");
        assertEquals(testDonationReward,testResult, "testCreateDonationReward: not matching");
    }

    /**
     * Tests "updateDonationReward()" function
     * Asserts that the given DonationReward is properly updated in the DonationReward array.
     * 
     * @throws IOException
     */
    @Test
    public void testUpdateDonationReward() throws IOException{
        DonationReward testDonationReward = new DonationReward("Art", 6000);
        DonationRewardFileDAO.updateData(testDonationReward);
        DonationReward testResult = DonationRewardFileDAO.getData("Art");

        assertEquals(testDonationReward,testResult, "testUpdateDonationReward");
    }
}
