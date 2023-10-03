package com.ufund.api.ufundapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ufund.api.ufundapi.persistence.NeedDAO;

import com.ufund.api.ufundapi.model.Need;

/**
 * Handles the REST API requests for the Need resource
 * <p>
 * {@literal @}RestController Spring annotation identifies this class as a REST API
 * method handler to the Spring framework
 * 
 * @author SWEN Faculty
 */

@RestController
@RequestMapping("needs")
public class NeedsController {
    private static final Logger LOG = Logger.getLogger(NeedsController.class.getName());
    private NeedDAO needDao;

    /**
     * Creates a REST API controller to reponds to requests
     * 
     * @param needDao The {@link NeedDAO Need Data Access Object} to perform CRUD operations
     * <br>
     * This dependency is injected by the Spring Framework
     */
    public NeedsController(NeedDAO needDao) {
        this.needDao = needDao;
    }
    /**
     * Responds to the GET request for a {@linkplain Need need} for the given id
     * 
     * @param id The id used to locate the {@link Need need}
     * 
     * @return ResponseEntity with {@link Need need} object and HTTP status of OK if found<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("/{id}")
    public ResponseEntity<Need> getNeed(@PathVariable String id) {
        LOG.info("GET /needs/" + id);
        try {
            Need hero = needDao.getNeed(id);
            if (hero != null)
                return new ResponseEntity<Need>(hero,HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Responds to the GET request for all {@linkplain Need needs}
     * 
     * @return ResponseEntity with array of {@link Need need} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("")
    public ResponseEntity<Need[]> getNeed() {
        LOG.info("GET /needs");
        try{
            Need[] needs = needDao.getNeeds();
            return new ResponseEntity<Need[]>(needs, HttpStatus.OK);
        }catch(IOException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // Replace below with your implementation
        //return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

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
    @GetMapping("/")
    public ResponseEntity<Need[]> searchNeeds(@RequestParam String name) {
        LOG.info("GET /needs/?name="+name);
        try{
            Need[] needs = needDao.searchNeeds(name);
            return new ResponseEntity<Need[]>(needs, HttpStatus.OK);
        }catch(IOException e){
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // Replace below with your implementation
        //return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
    /**
     * Creates a {@linkplain Need need} with the provided need object
     * 
     * @param need - The {@link Need need} to create
     * 
     * @return ResponseEntity with created {@link Need need} object and HTTP status of CREATED<br>
     * ResponseEntity with HTTP status of CONFLICT if {@link Need need} object already exists<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
   @PostMapping("")
    public ResponseEntity<Need> createNeed(@RequestBody Need need) {
        LOG.info("POST /needs " + need);
        try{
            System.out.println(need.toString());//Need to add something that checks if a need's id or name exists.

            Need newNeed = needDao.createNeed(need);
            if(newNeed == null){
                //Creates a need with an id always greater than the previous greatest.
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }else{
                return new ResponseEntity<Need>(newNeed, HttpStatus.CREATED);
            }

        }catch(IOException e){
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // Replace below with your implementation
        //return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
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
    @PutMapping("")
    public ResponseEntity<Need> updateHero(@RequestBody Need need) {
        LOG.info("PUT /needs " + need);
        try{
            Need updatedHero = needDao.updateNeed(need);
            if(updatedHero != null){
                return new ResponseEntity<Need>(updatedHero, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch(IOException e){
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // Replace below with your implementation
        //return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * Deletes a {@linkplain Need need} with the given id
     * 
     * @param id The id of the {@link Need need} to deleted
     * 
     * @return ResponseEntity HTTP status of OK if deleted<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Need> deleteNeed(@PathVariable String id) {
        LOG.info("DELETE /needs/" + id);
        try{
            boolean heroFound = needDao.deleteNeed(id);
            if(heroFound == true){
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }catch(IOException e){
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // Replace below with your implementation
        //return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
