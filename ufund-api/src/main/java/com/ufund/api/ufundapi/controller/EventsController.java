package com.ufund.api.ufundapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import java.util.logging.Logger;
import com.ufund.api.ufundapi.persistence.DataFileDAO;
import com.ufund.api.ufundapi.persistence.EventFileDAO;
import com.ufund.api.ufundapi.model.Event;

/**
 * Handles the REST API requests for the Event resource
 * <p>
 * {@literal @}RestController Spring annotation identifies this class as a REST API
 * method handler to the Spring framework
 * 
 */

//Annotation is a combination of @ResponseBody, @Controller annotation.
//@ResponseBody says all returned objects are automatically serialized into json.
//@Controller marks class as a web handler (Handles http requests)
@RestController
//Maps this class to a certain url.
@RequestMapping("events")
public class EventsController extends controllerInterface<Event> {
    //private DataFileDAO<Event> eventDao;
    
    /**
     * Creates a REST API controller to reponds to requests
     * 
     * @param eventDao The {@link DataFileDAO Event Data Access Object} to perform CRUD operations
     * <br>
     * This dependency is injected by the Spring Framework
     */
    public EventsController(DataFileDAO<Event> eventDao) {
        super(eventDao, Logger.getLogger(EventsController.class.getName()));
        //this.eventDao = eventDao;
    }
}
