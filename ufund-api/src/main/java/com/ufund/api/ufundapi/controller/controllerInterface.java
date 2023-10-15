package com.ufund.api.ufundapi.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.model.User;
import com.ufund.api.ufundapi.persistence.DataFileDAO;
import com.ufund.api.ufundapi.persistence.UserFileDAO;
//Seems like annotations in interface method declarations can be inherited. Neat
public abstract class controllerInterface<T> {
    private DataFileDAO<T> dataDao;//Null
    private final Logger LOG;
    controllerInterface(DataFileDAO<T> d, Logger l){
        dataDao = d;
        LOG = l;
    }

    @GetMapping("/{name}")
    public ResponseEntity<T> getData(@PathVariable String name){
        LOG.info("GET /cupboard/" + name);
        try {
            T User = dataDao.getData(name);
            if (User != null)
                return new ResponseEntity<T>(User,HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/{name}")
    public ResponseEntity<T> deleteData(@PathVariable String name){
        LOG.info("DELETE /cupboard/" + name);
        try{
            boolean UserFound = dataDao.deleteData(name);
            if(UserFound == true){
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }catch(IOException e){
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/")
    public ResponseEntity<T[]> searchDataArray(@RequestParam String name){
        LOG.info("GET /cupboard/?name="+name);
        try{
            T[] needs = dataDao.searchDataArray(name);
            return new ResponseEntity<T[]>(needs, HttpStatus.OK);
        }catch(IOException e){
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<T[]> getDataArray(){
        LOG.info("GET /data");
        try{
            T[] needs = dataDao.getDataArray();
            return new ResponseEntity<T[]>(needs, HttpStatus.OK);
        }catch(IOException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("")
    public ResponseEntity<T> updateData(@RequestBody T need){
        LOG.info("PUT /data " + need);
        try{
            T updatedUser = dataDao.updateData(need);
            if(updatedUser != null){
                return new ResponseEntity<T>(updatedUser, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch(IOException e){
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("")
    public ResponseEntity<T> createData(@RequestBody T need){
        LOG.info("POST /data " + need);
        try{
            T newData = dataDao.createData(need);
            if(newData == null){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }else{
                return new ResponseEntity<T>(newData, HttpStatus.CREATED);
            }

        }catch(IOException e){
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
