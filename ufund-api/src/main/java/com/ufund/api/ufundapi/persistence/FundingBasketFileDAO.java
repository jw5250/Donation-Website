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
 * Implements the functionality for JSON file-based peristance for FundingBasket
 * 
 * {@literal @}Component Spring annotation instantiates a single instance of this
 * class and injects the instance into other classes as needed
 * 
 */
@Component
public class FundingBasketFileDAO implements DataFileDAO<Need> {
    private static final Logger LOG = Logger.getLogger(FundingBasketFileDAO.class.getName());
    Map<String,Need> needs;   // Provides a local cache of the need objects
                                // so that we don't need to read from the file
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
    public FundingBasketFileDAO(@Value("${fundingbasket.file}") String filename,ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();  // load the needs from the file
    }

    /**
     * Generates an array of {@linkplain Need needs} from the tree map
     * 
     * @return  The array of {@link Need needs}, may be empty
     */
    private Need[] getCupboardArray() throws IOException {
        return getCupboardArray(null);
    }

    /**
     * Generates an array of {@linkplain Need needs} from the tree map for any
     * {@linkplain Need needs} that contains the text specified by containsText
     * <br>
     * If containsText is null, the array contains all of the {@linkplain Need needs}
     * in the tree map
     * 
     * @return  The array of {@link Need needs}, may be empty
     */
    private Need[] getCupboardArray(String containsText)  throws IOException{ // if containsText == null, no filter
        ArrayList<Need> needArrayList = new ArrayList<>();

        for (Need need : needs.values()) {
            if (containsText == null || need.getName().contains(containsText)) {
                needArrayList.add(need);
            }
        }

        Need[] needArray = new Need[needArrayList.size()];
        needArrayList.toArray(needArray);
        return needArray;
    }

    /**
     * Saves the {@linkplain Need needs} from the map into the file as an array of JSON objects
     * 
     * @return true if the {@link Need needs} were written successfully
     * 
     * @throws IOException when file cannot be accessed or written to
     */
    private boolean save() throws IOException {
        Need[] needArray = getCupboardArray();

        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(new File(filename),needArray);
        return true;
    }

    /**
     * Loads {@linkplain Need needs} from the JSON file into the map
     * <br>
     * 
     * @return true if the file was read successfully
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    private boolean load() throws IOException {
        needs = new TreeMap<>();
        String nextName = "";

        // Deserializes the JSON objects from the file into an array of needs
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file
        Need[] needArray = objectMapper.readValue(new File(filename),Need[].class);

        // Add each need to the tree map and keep track of the greatest id
        for (Need need : needArray) {
            needs.put(need.getName(),need);
            if (need.getName().compareTo(nextName) > 0){
                nextName = need.getName();
            }
        }
        return true;
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Need[] getDataArray()  throws IOException{
        synchronized(needs) {
            return getCupboardArray();
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Need[] searchDataArray(String containsText)  throws IOException{
        synchronized(needs) {
            return getCupboardArray(containsText);
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Need getData(String name)  throws IOException{
        synchronized(needs) {
            if (needs.containsKey(name))
                return needs.get(name);
            else
                return null;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Need createData(Need need) throws IOException {
        synchronized(needs) {
            
            Need exists = getData(need.getName());

            if(exists == null){
                Need newNeed = new Need(need.getName(), need.getType(), need.getCost(), need.getQuantity());
                needs.put(newNeed.getName(),newNeed);
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
    public Need updateData(Need need) throws IOException {
        synchronized(needs) {
            if (needs.containsKey(need.getName()) == false)
                return null;  // need does not exist

            needs.put(need.getName(),need);
            save(); // may throw an IOException
            return need;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public boolean deleteData(String name) throws IOException {
        synchronized(needs) {
            if (needs.containsKey(name)) {
                needs.remove(name);
                return save();
            }
            else
                return false;
        }
    }
}