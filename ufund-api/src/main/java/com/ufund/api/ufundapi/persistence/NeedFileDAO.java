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

import com.ufund.api.ufundapi.model.Need;

/**
 * Implements the functionality for JSON file-based peristance for Needs
 * 
 * {@literal @}Component Spring annotation instantiates a single instance of this
 * class and injects the instance into other classes as Needed
 * 
 */
@Component
public class NeedFileDAO implements DataFileDAO<Need> {
    private static final Logger LOG = Logger.getLogger(NeedFileDAO.class.getName());
    Map<String,Need> Needs;   // Provides a local cache of the Need objects
                                // so that we don't Need to read from the file
                                // each time
    private ObjectMapper objectMapper;  // Provides conversion between Need
                                        // objects and JSON text format written
                                        // to the file
    private String filename;    // Filename to read from and write to

    /**
     * Creates a Need File Data Access Object
     * 
     * @param filename Filename to read from and write to
     * @param objectMapper Provides JSON Object to/from Java Object serialization and deserialization
     * 
     * @throws IOException when file cannot be accessed or read from
     */
     //Is the ObjectMapper automatically inserted?
    public NeedFileDAO(@Value("${needs.file}") String filename,ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();  // load the Needs from the file
    }

    /**
     * Generates an array of {@linkplain Need Needs} from the tree map
     * 
     * @return  The array of {@link Need Needs}, may be empty
     */
    private Need[] getCupboardArray() {
        return getCupboardArray(null);
    }

    /**
     * Generates an array of {@linkplain Need Needs} from the tree map for any
     * {@linkplain Need Needs} that contains the text specified by containsText
     * <br>
     * If containsText is null, the array contains all of the {@linkplain Need Needs}
     * in the tree map
     * 
     * @return  The array of {@link Need Needs}, may be empty
     */
    private Need[] getCupboardArray(String containsText) { // if containsText == null, no filter
        ArrayList<Need> NeedArrayList = new ArrayList<>();

        for (Need Need : Needs.values()) {
            if (containsText == null || Need.getName().contains(containsText)) {
                NeedArrayList.add(Need);
            }
        }

        Need[] NeedArray = new Need[NeedArrayList.size()];
        NeedArrayList.toArray(NeedArray);
        return NeedArray;
    }

    /**
     * Saves the {@linkplain Need Needs} from the map into the file as an array of JSON objects
     * 
     * @return true if the {@link Need Needs} were written successfully
     * 
     * @throws IOException when file cannot be accessed or written to
     */
    private boolean save() throws IOException {
        Need[] NeedArray = getCupboardArray();
        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(new File(filename),NeedArray);
        return true;
    }

    /**
     * Loads {@linkplain Need Needs} from the JSON file into the map
     * <br>
     * 
     * @return true if the file was read successfully
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    private boolean load() throws IOException {
        Needs = new TreeMap<>();
        String nextName = "";

        // Deserializes the JSON objects from the file into an array of Needs
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file
        Need[] NeedArray = objectMapper.readValue(new File(filename),Need[].class);

        // Add each Need to the tree map and keep track of the greatest id
        for (Need Need : NeedArray) {
            Needs.put(Need.getName(),Need);
            if (Need.getName().compareTo(nextName) > 0){
                nextName = Need.getName();
            }
        }
        return true;
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Need[] getDataArray() throws IOException  {
        synchronized(Needs) {
            return getCupboardArray();
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Need[] searchDataArray(String containsText)throws IOException {
        synchronized(Needs) {
            return getCupboardArray(containsText);
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Need getData(String name) throws IOException  {
        synchronized(Needs) {
            if (Needs.containsKey(name))
                return Needs.get(name);
            else
                return null;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Need createData(Need Need) throws IOException {
        synchronized(Needs) {
            
            Need exists = getData(Need.getName());

            if(exists == null){
                Need newNeed = new Need(Need.getName(), Need.getType(), Need.getCost(), Need.getQuantity());
                Needs.put(newNeed.getName(),newNeed);
                save(); // may throw an IOException
                return newNeed;
            }else{
                return null;
            }
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Need updateData(Need Need) throws IOException {
        synchronized(Needs) {
            if (Needs.containsKey(Need.getName()) == false)
                return null;  // Need does not exist

            Needs.put(Need.getName(),Need);
            save(); // may throw an IOException
            return Need;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public boolean deleteData(String name) throws IOException {
        synchronized(Needs) {
            if (Needs.containsKey(name)) {
                Needs.remove(name);
                return save();
            }
            else
                return false;
        }
    }
}
