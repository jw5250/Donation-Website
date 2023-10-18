package com.ufund.api.ufundapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

import com.ufund.api.ufundapi.persistence.DataFileDAO;

import com.ufund.api.ufundapi.model.User;

/**
 * Handles the REST API requests for the User resource
 * <p>
 * {@literal @}RestController Spring annotation identifies this class as a REST API
 * method handler to the Spring framework
 * 
 */
//RestControllerMap is necessary for the request mapping annotations to process!
@RestController
@RequestMapping("users")
public class UserController extends controllerInterface<User>{
    //private UserFileDAO UserDao;

    /**
     * Creates a REST API controller to reponds to requests
     * 
     * @param UserDao The {@link UserDAO User Data Access Object} to perform CRUD operations
     * <br>
     * This dependency is injected by the Spring Framework
     */
    public UserController(DataFileDAO<User> UserDao) {
        super(UserDao, Logger.getLogger(UserController.class.getName()));
        //this.UserDao = UserDao;
    }
    /**
     * Responds to the GET request for a {@linkplain User User} for the given name
     * 
     * @param name The name used to locate the {@link User User}
     * 
     * @return ResponseEntity with {@link User User} object and HTTP status of OK if found<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    //@GetMapping("/{name}")
    
    /*@Override
    public ResponseEntity<User> getData(@PathVariable String name) {
        LOG.info("GET /cupboard/" + name);
        try {
            User User = UserDao.getData(name);
            if (User != null)
                return new ResponseEntity<User>(User,HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    /**
     * Responds to the GET request for all {@linkplain User Users}
     * 
     * @return ResponseEntity with array of {@link User User} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    //@GetMapping("")
    /*
    @Override
    public ResponseEntity<User[]> getDataArray() {
        LOG.info("GET /users");
        try{
            User[] Users = UserDao.getDataArray();
            return new ResponseEntity<User[]>(Users, HttpStatus.OK);
        }catch(IOException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    /**
     * Responds to the GET request for all {@linkplain User Users} whose name contains
     * the text in name
     * 
     * @param name The name parameter which contains the text used to find the {@link User User}
     * 
     * @return ResponseEntity with array of {@link User User} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     * <p>
     */
    //@GetMapping("/")
    /*@Override
    public ResponseEntity<User[]> searchDataArray(@RequestParam String name) {
        LOG.info("GET /cupboard/?name="+name);
        try{
            User[] Users = UserDao.searchDataArray(name);
            return new ResponseEntity<User[]>(Users, HttpStatus.OK);
        }catch(IOException e){
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
    /**
     * Creates a {@linkplain User User} with the provided User object
     * 
     * @param User - The {@link User User} to create
     * 
     * @return ResponseEntity with created {@link User User} object and HTTP status of CREATED<br>
     * ResponseEntity with HTTP status of CONFLICT if {@link User User} object already exists<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    //@PostMapping("")
    /*@Override
    public ResponseEntity<User> createData(@RequestBody User User) {
        LOG.info("POST /cupboard " + User);
        try{
            System.out.println(User.toString());//User to add something that checks if a User's name exists.

            User newUser = UserDao.createData(User);
            if(newUser == null){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }else{
                return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
            }

        }catch(IOException e){
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    /**
     * Updates the {@linkplain User User} with the provided {@linkplain User User} object, if it exists
     * 
     * @param User The {@link User User} to update
     * 
     * @return ResponseEntity with updated {@link User User} object and HTTP status of OK if updated<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    //@PutMapping("")
    /*@Override
    public ResponseEntity<User> updateData(@RequestBody User User) {
        LOG.info("PUT /cupboard " + User);
        try{
            User updatedUser = UserDao.updateData(User);
            if(updatedUser != null){
                return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch(IOException e){
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    /**
     * Deletes a {@linkplain User User} with the given name
     * 
     * @param name The name of the {@link User User} to deleted
     * 
     * @return ResponseEntity HTTP status of OK if deleted<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    //@DeleteMapping("/{name}")
    /*@Override
    public ResponseEntity<User> deleteData(@PathVariable String name) {
        LOG.info("DELETE /cupboard/" + name);
        try{
            boolean UserFound = UserDao.deleteData(name);
            if(UserFound == true){
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }catch(IOException e){
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
}
