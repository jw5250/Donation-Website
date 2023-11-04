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

import com.ufund.api.ufundapi.model.DonationReward;

/**
 * Implements the functionality for JSON file-based peristance for DonationRewards
 * 
 * {@literal @}Component Spring annotation instantiates a single instance of this
 * class and injects the instance into other classes as DonationRewarded
 * 
 */
@Component
public class DonationRewardFileDAO implements DataFileDAO<DonationReward> {
    private static final Logger LOG = Logger.getLogger(DonationRewardFileDAO.class.getName());
    Map<String,DonationReward> DonationRewards;   // Provides a local cache of the DonationReward objects
                                // so that we don't DonationReward to read from the file
                                // each time
    private ObjectMapper objectMapper;  // Provides conversion between DonationReward
                                        // objects and JSON text format written
                                        // to the file
    private String filename;    // Filename to read from and write to

    /**
     * Creates a DonationReward File Data Access Object
     * 
     * @param filename Filename to read from and write to
     * @param objectMapper Provides JSON Object to/from Java Object serialization and deserialization
     * 
     * @throws IOException when file cannot be accessed or read from
     */
     //Is the ObjectMapper automatically inserted?
    public DonationRewardFileDAO(@Value("${DonationRewards.file}") String filename,ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();  // load the DonationRewards from the file
    }

    /**
     * Generates an array of {@linkplain DonationReward DonationRewards} from the tree map
     * 
     * @return  The array of {@link DonationReward DonationRewards}, may be empty
     */
    private DonationReward[] getCupboardArray() {
        return getCupboardArray(null);
    }

    /**
     * Generates an array of {@linkplain DonationReward DonationRewards} from the tree map for any
     * {@linkplain DonationReward DonationRewards} that contains the text specified by containsText
     * <br>
     * If containsText is null, the array contains all of the {@linkplain DonationReward DonationRewards}
     * in the tree map
     * 
     * @return  The array of {@link DonationReward DonationRewards}, may be empty
     */
    private DonationReward[] getCupboardArray(String containsText) { // if containsText == null, no filter
        ArrayList<DonationReward> DonationRewardArrayList = new ArrayList<>();

        for (DonationReward DonationReward : DonationRewards.values()) {
            if (containsText == null || DonationReward.getName().contains(containsText)) {
                DonationRewardArrayList.add(DonationReward);
            }
        }

        DonationReward[] DonationRewardArray = new DonationReward[DonationRewardArrayList.size()];
        DonationRewardArrayList.toArray(DonationRewardArray);
        return DonationRewardArray;
    }

    /**
     * Saves the {@linkplain DonationReward DonationRewards} from the map into the file as an array of JSON objects
     * 
     * @return true if the {@link DonationReward DonationRewards} were written successfully
     * 
     * @throws IOException when file cannot be accessed or written to
     */
    private boolean save() throws IOException {
        DonationReward[] DonationRewardArray = getCupboardArray();
        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(new File(filename),DonationRewardArray);
        return true;
    }

    /**
     * Loads {@linkplain DonationReward DonationRewards} from the JSON file into the map
     * <br>
     * 
     * @return true if the file was read successfully
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    private boolean load() throws IOException {
        DonationRewards = new TreeMap<>();
        String nextName = "";

        // Deserializes the JSON objects from the file into an array of DonationRewards
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file
        DonationReward[] DonationRewardArray = objectMapper.readValue(new File(filename),DonationReward[].class);

        // Add each DonationReward to the tree map and keep track of the greatest id
        for (DonationReward DonationReward : DonationRewardArray) {
            DonationRewards.put(DonationReward.getName(),DonationReward);
            if (DonationReward.getName().compareTo(nextName) > 0){
                nextName = DonationReward.getName();
            }
        }
        return true;
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public DonationReward[] getDataArray() throws IOException  {
        synchronized(DonationRewards) {
            return getCupboardArray();
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public DonationReward[] searchDataArray(String containsText)throws IOException {
        synchronized(DonationRewards) {
            return getCupboardArray(containsText);
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public DonationReward getData(String name) throws IOException  {
        synchronized(DonationRewards) {
            if (DonationRewards.containsKey(name))
                return DonationRewards.get(name);
            else
                return null;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public DonationReward createData(DonationReward DonationReward) throws IOException {
        synchronized(DonationRewards) {
            
            DonationReward exists = getData(DonationReward.getName());

            if(exists == null){
                DonationReward newDonationReward = new DonationReward(DonationReward.getName(), DonationReward.getRequirement(), DonationReward.getQuantity());
                DonationRewards.put(newDonationReward.getName(),newDonationReward);
                save(); // may throw an IOException
                return newDonationReward;
            }else{
                return null;
            }
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public DonationReward updateData(DonationReward DonationReward) throws IOException {
        synchronized(DonationRewards) {
            if (DonationRewards.containsKey(DonationReward.getName()) == false)
                return null;  // DonationReward does not exist

            DonationRewards.put(DonationReward.getName(),DonationReward);
            save(); // may throw an IOException
            return DonationReward;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public boolean deleteData(String name) throws IOException {
        synchronized(DonationRewards) {
            if (DonationRewards.containsKey(name)) {
                DonationRewards.remove(name);
                return save();
            }
            else
                return false;
        }
    }
}
