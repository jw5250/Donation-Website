package com.ufund.api.ufundapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.ufund.api.ufundapi.persistence.NeedFileDAO;
import com.ufund.api.ufundapi.persistence.NeedDAO;
import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.controller.NeedsController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Test the Need Controller class
 * 
 * @author SWEN Faculty
 */
@Tag("Controller-tier")
public class needControllerTest {
    private NeedsController NeedController;
    private NeedFileDAO mockNeedDAO;

    /**
     * Before each test, create a new NeedController object and inject
     * a mock Need DAO
     */
    @BeforeEach
    public void setupNeedController() {
        mockNeedDAO = mock(NeedFileDAO.class);
        NeedController = new NeedsController(mockNeedDAO);
    }
    /**
     * This function tests if the function for getting some element from a Needbase in the REST api works. Test for:
     * The server response given a sucessful GET
     * The server response given an unsucessful GET (nonexistent entity requested)
     * The server response given an IOexception.
     * @throws IOException
    */
    @Test
    public void testGet() throws IOException {  // getData may throw IOException

        Need Need = new Need("thing1", "type1", 99, 10);

        when(mockNeedDAO.getData(Need.getName())).thenReturn(Need);


        ResponseEntity<Need> response = NeedController.getData(Need.getName());


        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(Need,response.getBody());

        String NeedId = "doesntwork";

        when(mockNeedDAO.getData(NeedId)).thenReturn(null);


        response = NeedController.getData(NeedId);


        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());


        doThrow(new IOException()).when(mockNeedDAO).getData(NeedId);


        response = NeedController.getData(NeedId);


        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }


    /**
     * This function tests if the function for creating a new Need of the REST api works. Test for:
     * The server response given a sucessful creation
     * The server response given an unsucessful creation (entity with same name exists already)
     * The server response given an IOexception.
     * @throws IOException
    */
    @Test
    public void testcreateData() throws IOException {  // createData may throw IOException
        Need Need = new Need("thing9", "type15", 9, 10);

        when(mockNeedDAO.createData(Need)).thenReturn(Need);


        ResponseEntity<Need> response = NeedController.createData(Need);


        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(Need,response.getBody());

        Need = new Need("thing9", "type15", 9, 10);

        when(mockNeedDAO.createData(Need)).thenReturn(null);


        response = NeedController.createData(Need);


        assertEquals(HttpStatus.CONFLICT,response.getStatusCode());



        doThrow(new IOException()).when(mockNeedDAO).createData(Need);

        response = NeedController.createData(Need);


        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    /**
     * This function tests if the function deleting a Need with a name of the REST api works. Test for:
     * The server response given a successful update
     * The server response given an unsuccessful update (nonexistent target)
     * The server response given an IOexception.
     * @throws IOException
    */
    @Test
    public void testupdateData() throws IOException { // updateData may throw IOException
        
        Need Need = new Need("thing10", "type10", 9, 100);
        
        when(mockNeedDAO.updateData(Need)).thenReturn(Need);
        ResponseEntity<Need> response = NeedController.updateData(Need);
        Need.setName("thing-1");


        response = NeedController.updateData(Need);


        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(Need,response.getBody());


        Need = new Need("thing10", "type10", 9, 100);
        
        when(mockNeedDAO.updateData(Need)).thenReturn(null);


        response = NeedController.updateData(Need);


        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());



        doThrow(new IOException()).when(mockNeedDAO).updateData(Need);


        response = NeedController.updateData(Need);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());


    }
    /**
     * This function tests if the function getting all Needs of the REST api works. Test for:
     * The server response given all Needs have been sent to the client.
     * The server response given an IOexception.
     * @throws IOException
    */
    @Test
    public void testgetDataArray() throws IOException { // getDataArray may throw IOException

        Need[] Needs = new Need[2];
        Needs[0] = new Need("thing10", "type10", 9, 100);
        Needs[1] = new Need("thing5", "type0", 1, 1040);

        when(mockNeedDAO.getDataArray()).thenReturn(Needs);


        ResponseEntity<Need[]> response = NeedController.getDataArray();


        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(Needs,response.getBody());

        doThrow(new IOException()).when(mockNeedDAO).getDataArray();


        response = NeedController.getDataArray();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
    /**
     * This function tests if the search function of the REST api works. Test for:
     * The server response given searching and finding something with a given substring.
     * The server response given an IOexception.
     * @throws IOException
    */
    @Test
    public void testsearchDataArray() throws IOException { // searchDataArray may throw IOException
        String searchString = "ing";
        Need[] Needs = new Need[2];
        Needs[0] = new Need("thing10", "type10", 9, 100);
        Needs[1] = new Need("thing9", "type15", 9, 10);
        
        when(mockNeedDAO.searchDataArray(searchString)).thenReturn(Needs);


        ResponseEntity<Need[]> response = NeedController.searchDataArray(searchString);


        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(Needs,response.getBody());


        searchString = "an";

        doThrow(new IOException()).when(mockNeedDAO).searchDataArray(searchString);


        response = NeedController.searchDataArray(searchString);


        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
    /**
     * This function tests if the delete function of the REST api works. Test for:
     * The response code after a successful deletion.
     * The response code given an entity isn't there.
     * The server response given an IOexception.
     * @throws IOException
    */
    @Test
    public void testdeleteData() throws IOException {

        String NeedId = "thing1";

        when(mockNeedDAO.deleteData(NeedId)).thenReturn(true);


        ResponseEntity<Need> response = NeedController.deleteData(NeedId);


        assertEquals(HttpStatus.OK,response.getStatusCode());




        when(mockNeedDAO.deleteData(NeedId)).thenReturn(false);


        response = NeedController.deleteData(NeedId);


        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());


        doThrow(new IOException()).when(mockNeedDAO).deleteData(NeedId);


        response = NeedController.deleteData(NeedId);


        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
}
