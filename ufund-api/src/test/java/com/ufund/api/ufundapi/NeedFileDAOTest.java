package com.ufund.api.ufundapi;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

@Tag("Persistence-tier")
public class NeedFileDAOTest {
    NeedFileDAO needFileDAO;
    Need[] testNeeds;
    ObjectMapper mockObjectMapper;
    @BeforeEach
    public void setupNeedFileDAO() throws IOException {
        mockObjectMapper = mock(ObjectMapper.class);

        Need[] testNeeds = new Need[3];
        testNeeds[0] = new Need("Art", "funding", 10000, 5);
        testNeeds[1] = new Need("Teachers", "volunteer", 0, 10);
        testNeeds[2] = new Need("null", "null", 0, 0);

        File dummy = new File("none.txt");
        when(mockObjectMapper
            .readValue("none.txt",Need[].class))
                .thenReturn(testNeeds);
        needFileDAO = new NeedFileDAO("none.txt",mockObjectMapper);
    }   
    @Test
    public void testGetCupboardArray() throws IOException{
        Need[] testArray = new Need[3];
        testArray[0] = new Need("Art", "funding", 10000, 5);
        testArray[1] = new Need("Teachers", "volunteer", 0, 10);
        testArray[2] = new Need("null", "null", 0, 0);

        Need[] testResult = needFileDAO.getDataArray();

        assertArrayEquals(testArray, testResult, "testGetCupboardArray");
    }
    @Test
    public void testSearchCupboard() throws IOException{
        Need[] testArray = new Need[2];
        testArray[0] = new Need("Art", "funding", 10000, 5);
        testArray[1] = new Need("Teachers", "volunteer", 0, 10);

        Need[] testResult = needFileDAO.searchDataArray("r");

        assertArrayEquals(testArray, testResult, "testGetCupboardArray");
    }
    @Test
    public void testGetNeed() {}
    @Test
    public void testDeleteNeed() {}
    @Test
    public void testCreateNeed() {}
    @Test
    public void testUpdateNeed() {}

}
