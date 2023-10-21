package com.ufund.api.ufundapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ufund.api.ufundapi.model.User;

/**
 * Implements the functionality for JSON file-based peristance for Users
 * 
 * {@literal @}Component Spring annotation instantiates a single instance of this
 * class and injects the instance into other classes as Usered
 * 
 */
@Component
public class UserFileDAO implements DataFileDAO<User> {
    private static final Logger LOG = Logger.getLogger(UserFileDAO.class.getName());
    Map<String,User> Users;   // Provides a local cache of the User objects
                                // so that we don't User to read from the file
                                // each time
    private ObjectMapper objectMapper;  // Provides conversion between User
                                        // objects and JSON text format written
                                        // to the file
    private String filename;    // Filename to read from and write to

    /**
     * Creates a User File Data Access Object
     * 
     * @param filename Filename to read from and write to
     * @param objectMapper Provides JSON Object to/from Java Object serialization and deserialization
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    public UserFileDAO(@Value("${users.file}") String filename,ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();  // load the Users from the file
    }

    /**
     * Generates an array of {@linkplain User Users} from the tree map
     * 
     * @return  The array of {@link User Users}, may be empty
     */
    private User[] getUserArray() {
        return getUserArray(null);
    }

    /**
     * Generates an array of {@linkplain User Users} from the tree map for any
     * {@linkplain User Users} that contains the text specified by containsText
     * <br>
     * If containsText is null, the array contains all of the {@linkplain User Users}
     * in the tree map
     * 
     * @return  The array of {@link User Users}, may be empty
     */
    private User[] getUserArray(String containsText) { // if containsText == null, no filter
        ArrayList<User> UserArrayList = new ArrayList<>();

        for (User User : Users.values()) {
            if (containsText == null || User.getName().contains(containsText)) {
                UserArrayList.add(User);
            }
        }

        User[] UserArray = new User[UserArrayList.size()];
        UserArrayList.toArray(UserArray);
        return UserArray;
    }

    /**
     * Saves the {@linkplain User Users} from the map into the file as an array of JSON objects
     * 
     * @return true if the {@link User Users} were written successfully
     * 
     * @throws IOException when file cannot be accessed or written to
     */
    private boolean save() throws IOException {
        User[] UserArray = getUserArray();
        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(new File(filename),UserArray);
        return true;
    }

    /**
     * Loads {@linkplain User Users} from the JSON file into the map
     * <br>
     * 
     * @return true if the file was read successfully
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    private boolean load() throws IOException {
        Users = new TreeMap<>();
        String nextName = "";

        // Deserializes the JSON objects from the file into an array of Users
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file
        User[] UserArray = objectMapper.readValue(new File(filename),User[].class);

        // Add each User to the tree map and keep track of the greatest id
        for (User User : UserArray) {
            Users.put(User.getName(),User);
            if (User.getName().compareTo(nextName) > 0){
                nextName = User.getName();
            }
        }
        return true;
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public User[] getDataArray()throws IOException  {
        synchronized(Users) {
            return getUserArray();
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public User[] searchDataArray(String containsText)throws IOException  {
        synchronized(Users) {
            return getUserArray(containsText);
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public User getData(String name)throws IOException {
        synchronized(Users) {
            if (Users.containsKey(name))
                return Users.get(name);
            else
                return null;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public User createData(User User) throws IOException {
        synchronized(Users) {
            
            User exists = getData(User.getName());

            if(exists == null){
                User newUser = new User(User.getName(), User.getIsManager(), User.getFundingBasket());
                Users.put(newUser.getName(),newUser);
                save(); // may throw an IOException
                return newUser;
            }else{
                return null;
            }
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public User updateData(User User) throws IOException {
        synchronized(Users) {
            if (Users.containsKey(User.getName()) == false)
                return null;  // User does not exist

            Users.put(User.getName(),User);
            save(); // may throw an IOException
            return User;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public boolean deleteData(String name) throws IOException {
        synchronized(Users) {
            if (Users.containsKey(name)) {
                Users.remove(name);
                return save();
            }
            else
                return false;
        }
    }
}