package com.ufund.api.ufundapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import java.util.logging.Logger;
import com.ufund.api.ufundapi.persistence.DataFileDAO;

import com.ufund.api.ufundapi.model.Need;

/**
 * Handles the REST API requests for the Need resource
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
@RequestMapping("cupboard")
public class NeedsController extends controllerInterface<Need> {
    //private DataFileDAO<Need> needDao;
    
    /**
     * Creates a REST API controller to reponds to requests
     * 
     * @param needDao The {@link DataFileDAO Need Data Access Object} to perform CRUD operations
     * <br>
     * This dependency is injected by the Spring Framework
     */
    public NeedsController(DataFileDAO<Need> needDao) {
        super(needDao, Logger.getLogger(NeedsController.class.getName()));
        //this.needDao = needDao;
    }
    /**
     * Responds to the GET request for a {@linkplain Need need} for the given name
     * 
     * @param name The name used to locate the {@link Need need}
     * 
     * @return ResponseEntity with {@link Need need} object and HTTP status of OK if found<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    //@GetMapping("/{name}")
    /* 
    @Override
    public ResponseEntity<Need> getData(@PathVariable String name) {
        //LOG.info("GET /cupboard/" + name);
        try {
            Need need = needDao.getData(name);
            if (need != null)
                return new ResponseEntity<Need>(need,HttpStatus.OK);
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
     * Responds to the GET request for all {@linkplain Need needs}
     * 
     * @return ResponseEntity with array of {@link Need need} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    //@GetMapping("")
    /*
    @Override
    public ResponseEntity<Need[]> getDataArray() {
        //LOG.info("GET /cupboard");
        try{
            Need[] needs = needDao.getDataArray();
            return new ResponseEntity<Need[]>(needs, HttpStatus.OK);
        }catch(IOException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    */
    /**
     * Responds to the GET request for all {@linkplain Need needs} whose name contains
     * the text in name
     * 
     * @param name The name parameter which contains the text used to find the {@link Need need}
     * 
     * @return ResponseEntity with array of {@link Need need} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     * <p>
     */
    //@GetMapping("/")
    /*
    @Override
    public ResponseEntity<Need[]> searchDataArray(@RequestParam String name) {
        //LOG.info("GET /cupboard/?name="+name);
        try{
            Need[] needs = needDao.searchDataArray(name);
            return new ResponseEntity<Need[]>(needs, HttpStatus.OK);
        }catch(IOException e){
            //LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    */
    /**
     * Creates a {@linkplain Need need} with the provided need object
     * 
     * @param need - The {@link Need need} to create
     * 
     * @return ResponseEntity with created {@link Need need} object and HTTP status of CREATED<br>
     * ResponseEntity with HTTP status of CONFLICT if {@link Need need} object already exists<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    //@PostMapping("")
    /* 
    @Override
    public ResponseEntity<Need> createData(@RequestBody Need need) {
        //LOG.info("POST /cupboard " + need);
        try{
            System.out.println(need.toString());//Need to add something that checks if a need's name exists.

            Need newNeed = needDao.createData(need);
            if(newNeed == null){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }else{
                return new ResponseEntity<Need>(newNeed, HttpStatus.CREATED);
            }

        }catch(IOException e){
            //LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    */
    //Works
    /**
     * Updates the {@linkplain Need need} with the provided {@linkplain Need need} object, if it exists
     * 
     * @param need The {@link Need need} to update
     * 
     * @return ResponseEntity with updated {@link Need need} object and HTTP status of OK if updated<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    //@PutMapping("")
    /* 
    @Override
    public ResponseEntity<Need> updateData(@RequestBody Need need) {
        //LOG.info("PUT /cupboard " + need);
        try{
            Need updatedNeed = needDao.updateData(need);
            if(updatedNeed != null){
                return new ResponseEntity<Need>(updatedNeed, HttpStatus.OK);
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
     * Deletes a {@linkplain Need need} with the given name
     * 
     * @param name The name of the {@link Need need} to deleted
     * 
     * @return ResponseEntity HTTP status of OK if deleted<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    //@DeleteMapping("/{name}")
    /* 
    @Override
    public ResponseEntity<Need> deleteData(@PathVariable String name) {
        //LOG.info("DELETE /cupboard/" + name);
        try{
            boolean needFound = needDao.deleteData(name);
            if(needFound == true){
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
