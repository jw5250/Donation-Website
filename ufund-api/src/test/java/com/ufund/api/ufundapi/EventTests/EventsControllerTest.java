package com.ufund.api.ufundapi.EventTests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.ufund.api.ufundapi.persistence.DataFileDAO;
import com.ufund.api.ufundapi.model.Event;
import com.ufund.api.ufundapi.persistence.EventFileDAO;
import com.ufund.api.ufundapi.controller.EventsController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class EventsControllerTest {
    private EventsController DataController;
    private DataFileDAO<Event> mockDataDAO;

    /**
     * Before each test, create a new DataController object and inject
     * a mock Data DAO
     */

     //Indicate 
    @BeforeEach
    
    public void setupDataController() {
        //mock will fill in class dependencies if they exist with default values and/or compiler based things.
        mockDataDAO = mock(EventFileDAO.class);//Use of mock will fill in constructor dependencies.
        DataController = new EventsController(mockDataDAO);
    }
    /**
     * This function tests if the function for getting some element from a database in the REST api works. Test for:
     * The server response given a sucessful GET
     * The server response given an unsucessful GET (nonexistent entity requested)
     * The server response given an IOexception.
     * @throws IOException
    */
    @Test
    public void testGet() throws IOException {  // getData may throw IOException

        Event Data = new Event("thing10", "dec", "1200");

        //Always have the mock object always return the "correct" value.
        when(mockDataDAO.getData(Data.getName())).thenReturn(Data);
        

        ResponseEntity<Event> response = DataController.getData(Data.getName());


        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(Data,response.getBody());

        String DataId = "doesntwork";

        when(mockDataDAO.getData(DataId)).thenReturn(null);


        response = DataController.getData(DataId);


        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());

        //None of the IOexception functions work properly. why?
        doThrow(new IOException()).when(mockDataDAO).getData(DataId);


        response = DataController.getData(DataId);


        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }


    /**
     * This function tests if the function for creating a new Data of the REST api works. Test for:
     * The server response given a sucessful creation
     * The server response given an unsucessful creation (entity with same name exists already)
     * The server response given an IOexception.
     * @throws IOException
    */
    //Demarkate this as a test
    //Justin Wu's work:
    @Test
    public void testCreateData() throws IOException {  // createData may throw IOException
        Event Data = new Event("thing10", "dec", "1200");

        when(mockDataDAO.createData(Data)).thenReturn(Data);


        ResponseEntity<Event> response = DataController.createData(Data);


        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(Data,response.getBody());

        Data = new Event("thing10", "dec", "1200");

        when(mockDataDAO.createData(Data)).thenReturn(null);


        response = DataController.createData(Data);


        assertEquals(HttpStatus.CONFLICT,response.getStatusCode());



        doThrow(new IOException()).when(mockDataDAO).createData(Data);

        response = DataController.createData(Data);


        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    /**
     * This function tests if the function deleting a Data with a name of the REST api works. Test for:
     * The server response given a successful update
     * The server response given an unsuccessful update (nonexistent target)
     * The server response given an IOexception.
     * @throws IOException
    */
    @Test
    public void testUpdateData() throws IOException { // updateData may throw IOException
        
        Event Data = new Event("thing10", "dec", "1200");
        
        when(mockDataDAO.updateData(Data)).thenReturn(Data);
        ResponseEntity<Event> response = DataController.updateData(Data);
        Data.setName("thing-1");


        response = DataController.updateData(Data);


        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(Data,response.getBody());


        Data = new Event("thing10", "dec", "1200");
        
        when(mockDataDAO.updateData(Data)).thenReturn(null);


        response = DataController.updateData(Data);


        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());



        doThrow(new IOException()).when(mockDataDAO).updateData(Data);


        response = DataController.updateData(Data);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());


    }
    /**
     * This function tests if the function getting all Datas of the REST api works. Test for:
     * The server response given all Datas have been sent to the client.
     * The server response given an IOexception.
     * @throws IOException
    */
    @Test
    public void testGetCupboard() throws IOException { // getCupboard may throw IOException

        Event[] Datas = new Event[2];
        Datas[0] = new Event("thing10", "mar", "1900");
        Datas[1] = new Event("thing5", "may", "1040");

        when(mockDataDAO.getDataArray()).thenReturn(Datas);


        ResponseEntity<Event[]> response = DataController.getDataArray();


        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(Datas,response.getBody());

        doThrow(new IOException()).when(mockDataDAO).getDataArray();


        response = DataController.getDataArray();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
    /**
     * This function tests if the search function of the REST api works. Test for:
     * The server response given searching and finding something with a given substring.
     * The server response given an IOexception.
     * @throws IOException
    */
    @Test
    public void testSearchDatas() throws IOException { // searchDatas may throw IOException
        String searchString = "ing";
        Event[] Datas = new Event[2];
        Datas[0] = new Event("thing10", "jan", "1000");
        Datas[1] = new Event("thing9", "feb", "1030");
        
        when(mockDataDAO.searchDataArray(searchString)).thenReturn(Datas);


        ResponseEntity<Event[]> response = DataController.searchDataArray(searchString);


        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(Datas,response.getBody());


        searchString = "an";

        doThrow(new IOException()).when(mockDataDAO).searchDataArray(searchString);


        response = DataController.searchDataArray(searchString);


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
    public void testDeleteData() throws IOException {

        String DataId = "thing1";

        when(mockDataDAO.deleteData(DataId)).thenReturn(true);


        ResponseEntity<Event> response = DataController.deleteData(DataId);


        assertEquals(HttpStatus.OK,response.getStatusCode());




        when(mockDataDAO.deleteData(DataId)).thenReturn(false);


        response = DataController.deleteData(DataId);


        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());

        //deletData will run correctly given the doThrow exception, for some reason.
        doThrow(new IOException()).when(mockDataDAO).deleteData(DataId);


        response = DataController.deleteData(DataId);


        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
}
