package com.ufund.api.ufundapi.persistence;

import java.io.IOException;
import com.ufund.api.ufundapi.model.Need;

/**
 * Defines the interface for Need object persistence
 * 
 */
//Should this be generic?
public interface DataFileDAO<T>{
    /**
     * Retrieves all {@linkplain Need Needs}
     * 
     * @return An array of {@link Need Need} objects, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    T[] getDataArray() throws IOException;

    /**
     * Finds all {@linkplain Need Needs} whose name contains the given text
     * 
     * @param containsText The text to match against
     * 
     * @return An array of {@link Need Needs} whose nemes contains the given text, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    T[] searchDataArray(String containsText) throws IOException;

    /**
     * Retrieves a {@linkplain Need Need} with the given Need
     * 
     * @param NeedName The name of the {@link Need Need} to get
     * 
     * @return a {@link Need Need} object with the matching name
     * <br>
     * null if no {@link Need Need} with a matching name is found
     * 
     * @throws IOException if an issue with underlying storage
     */
    T getData(String NeedName) throws IOException;

    /**
     * Creates and saves a {@linkplain Need Need}
     * 
     * @param Need {@linkplain Need Need} object to be created and saved
     * <br>
     * Made a new Need with a name, a type, a cost, and the amount available.
     *
     * @return new {@link Need Need} if successful, false otherwise 
     * 
     * @throws IOException if an issue with underlying storage
     */
    T createData(T Need) throws IOException;

    /**
     * Updates and saves a {@linkplain Need Need}
     * 
     * @param {@link Need Need} object to be updated and saved
     * 
     * @return updated {@link Need Need} if successful, null if
     * {@link Need Need} could not be found
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    T updateData(T Need) throws IOException;

    /**
     * Deletes a {@linkplain Need Need} with the given name
     * 
     * @param name The name of the {@link Need Need}
     * 
     * @return true if the {@link Need Need} was deleted
     * <br>
     * false if Need with the given name does not exist
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    boolean deleteData(String name) throws IOException;
}
