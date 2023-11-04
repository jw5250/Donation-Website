package com.ufund.api.ufundapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import java.util.logging.Logger;
import com.ufund.api.ufundapi.persistence.DataFileDAO;
import com.ufund.api.ufundapi.persistence.EventFileDAOFileDAO;
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
@RequestMapping("Event")
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
    /**
     * Responds to the GET request for a {@linkplain Event event} for the given name
     * 
     * @param name The name used to locate the {@link Event event}
     * 
     * @return ResponseEntity with {@link Event event} object and HTTP status of OK if found<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    //@GetMapping("/{name}")
    /* 
    @Override
    public ResponseEntity<Event> getData(@PathVariable String name) {
        //LOG.info("GET /Event/" + name);
        try {
            Event event = eventDao.getData(name);
            if (event != null)
                return new ResponseEntity<Event>(event,HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            //LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    */

    /**
     * Responds to the GET request for all {@linkplain Event events}
     * 
     * @return ResponseEntity with array of {@link Event event} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    //@GetMapping("")
    /*
    @Override
    public ResponseEntity<Event[]> getDataArray() {
        //LOG.info("GET /Event");
        try{
            Event[] events = eventDao.getDataArray();
            return new ResponseEntity<Event[]>(events, HttpStatus.OK);
        }catch(IOException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    */
    /**
     * Responds to the GET request for all {@linkplain Event events} whose name contains
     * the text in name
     * 
     * @param name The name parameter which contains the text used to find the {@link Event event}
     * 
     * @return ResponseEntity with array of {@link Event event} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     * <p>
     */
    //@GetMapping("/")
    /*
    @Override
    public ResponseEntity<Event[]> searchDataArray(@RequestParam String name) {
        //LOG.info("GET /Event/?name="+name);
        try{
            Event[] events = eventDao.searchDataArray(name);
            return new ResponseEntity<Event[]>(events, HttpStatus.OK);
        }catch(IOException e){
            //LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    */
    /**
     * Creates a {@linkplain Event event} with the provided event object
     * 
     * @param event - The {@link Event event} to create
     * 
     * @return ResponseEntity with created {@link Event event} object and HTTP status of CREATED<br>
     * ResponseEntity with HTTP status of CONFLICT if {@link Event event} object already exists<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    //@PostMapping("")
    /* 
    @Override
    public ResponseEntity<Event> createData(@RequestBody Event event) {
        //LOG.info("POST /Event " + event);
        try{
            System.out.println(event.toString());//Event to add something that checks if a event's name exists.

            Event newEvent = eventDao.createData(event);
            if(newEvent == null){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }else{
                return new ResponseEntity<Event>(newEvent, HttpStatus.CREATED);
            }

        }catch(IOException e){
            //LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    */
    //Works
    /**
     * Updates the {@linkplain Event event} with the provided {@linkplain Event event} object, if it exists
     * 
     * @param event The {@link Event event} to update
     * 
     * @return ResponseEntity with updated {@link Event event} object and HTTP status of OK if updated<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    //@PutMapping("")
    /* 
    @Override
    public ResponseEntity<Event> updateData(@RequestBody Event event) {
        //LOG.info("PUT /cupboard " + event);
        try{
            Event updatedEvent = eventDao.updateData(event);
            if(updatedEvent != null){
                return new ResponseEntity<Event>(updatedEvent, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch(IOException e){
            //LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    */

    /**
     * Deletes a {@linkplain Event event} with the given name
     * 
     * @param name The name of the {@link Event event} to deleted
     * 
     * @return ResponseEntity HTTP status of OK if deleted<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    //@DeleteMapping("/{name}")
    /* 
    @Override
    public ResponseEntity<Event> deleteData(@PathVariable String name) {
        //LOG.info("DELETE /cupboard/" + name);
        try{
            boolean eventFound = eventDao.deleteData(name);
            if(eventFound == true){
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }catch(IOException e){
            //LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
}
