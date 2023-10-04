package com.ufund.api.ufundapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

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
    private NeedsController needController;
    private NeedDAO mockNeedDAO;

    /**
     * Before each test, create a new needController object and inject
     * a mock Need DAO
     */
    @BeforeEach
    public void setupneedController() {
        mockNeedDAO = mock(NeedDAO.class);
        needController = new NeedsController(mockNeedDAO);
    }
    /**
     * This function tests if the function for getting some element from a database in the REST api works. Test for:
     * The server response given a sucessful GET
     * The server response given an unsucessful GET (nonexistent entity requested)
     * The server response given an IOexception.
     * @throws IOException
    */
    @Test
    public void testGet() throws IOException {  // getNeed may throw IOException

        Need Need = new Need("thing1", "type1", 99, 10);

        when(mockNeedDAO.getNeed(Need.getName())).thenReturn(Need);


        ResponseEntity<Need> response = needController.getNeed(Need.getName());


        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(Need,response.getBody());

        String NeedId = "doesntwork";

        when(mockNeedDAO.getNeed(NeedId)).thenReturn(null);


        response = needController.getNeed(NeedId);


        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());


        doThrow(new IOException()).when(mockNeedDAO).getNeed(NeedId);


        response = needController.getNeed(NeedId);


        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }


    /**
     * This function tests if the function for creating a new need of the REST api works. Test for:
     * The server response given a sucessful creation
     * The server response given an unsucessful creation (entity with same name exists already)
     * The server response given an IOexception.
     * @throws IOException
    */
    @Test
    public void testCreateNeed() throws IOException {  // createNeed may throw IOException
        Need Need = new Need("thing9", "type15", 9, 10);

        when(mockNeedDAO.createNeed(Need)).thenReturn(Need);


        ResponseEntity<Need> response = needController.createNeed(Need);


        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(Need,response.getBody());

        Need = new Need("thing9", "type15", 9, 10);

        when(mockNeedDAO.createNeed(Need)).thenReturn(null);


        response = needController.createNeed(Need);


        assertEquals(HttpStatus.CONFLICT,response.getStatusCode());



        doThrow(new IOException()).when(mockNeedDAO).createNeed(Need);

        response = needController.createNeed(Need);


        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    /**
     * This function tests if the function deleting a need with a name of the REST api works. Test for:
     * The server response given a successful update
     * The server response given an unsuccessful update (nonexistent target)
     * The server response given an IOexception.
     * @throws IOException
    */
    @Test
    public void testUpdateNeed() throws IOException { // updateNeed may throw IOException
        
        Need Need = new Need("thing10", "type10", 9, 100);
        
        when(mockNeedDAO.updateNeed(Need)).thenReturn(Need);
        ResponseEntity<Need> response = needController.updateNeed(Need);
        Need.setName("thing-1");


        response = needController.updateNeed(Need);


        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(Need,response.getBody());


        Need = new Need("thing10", "type10", 9, 100);
        
        when(mockNeedDAO.updateNeed(Need)).thenReturn(null);


        response = needController.updateNeed(Need);


        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());



        doThrow(new IOException()).when(mockNeedDAO).updateNeed(Need);


        response = needController.updateNeed(Need);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());


    }
    /**
     * This function tests if the function getting all needs of the REST api works. Test for:
     * The server response given all needs have been sent to the client.
     * The server response given an IOexception.
     * @throws IOException
    */
    @Test
    public void testgetCupboard() throws IOException { // getCupboard may throw IOException

        Need[] Needs = new Need[2];
        Needs[0] = new Need("thing10", "type10", 9, 100);
        Needs[1] = new Need("thing5", "type0", 1, 1040);

        when(mockNeedDAO.getCupboard()).thenReturn(Needs);


        ResponseEntity<Need[]> response = needController.getCupboard();


        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(Needs,response.getBody());

        doThrow(new IOException()).when(mockNeedDAO).getCupboard();


        response = needController.getCupboard();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
    /**
     * This function tests if the search function of the REST api works. Test for:
     * The server response given searching and finding something with a given substring.
     * The server response given an IOexception.
     * @throws IOException
    */
    @Test
    public void testSearchNeeds() throws IOException { // searchNeeds may throw IOException
        String searchString = "ing";
        Need[] Needs = new Need[2];
        Needs[0] = new Need("thing10", "type10", 9, 100);
        Needs[1] = new Need("thing9", "type15", 9, 10);
        
        when(mockNeedDAO.searchNeeds(searchString)).thenReturn(Needs);


        ResponseEntity<Need[]> response = needController.searchNeeds(searchString);


        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(Needs,response.getBody());


        searchString = "an";

        doThrow(new IOException()).when(mockNeedDAO).searchNeeds(searchString);


        response = needController.searchNeeds(searchString);


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
    public void testDeleteNeed() throws IOException {

        String NeedId = "thing1";

        when(mockNeedDAO.deleteNeed(NeedId)).thenReturn(true);


        ResponseEntity<Need> response = needController.deleteNeed(NeedId);


        assertEquals(HttpStatus.OK,response.getStatusCode());




        when(mockNeedDAO.deleteNeed(NeedId)).thenReturn(false);


        response = needController.deleteNeed(NeedId);


        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());


        doThrow(new IOException()).when(mockNeedDAO).deleteNeed(NeedId);


        response = needController.deleteNeed(NeedId);


        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
}
