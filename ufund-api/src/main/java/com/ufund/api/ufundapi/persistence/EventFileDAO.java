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

import com.ufund.api.ufundapi.model.Event;

/**
 * Implements the functionality for JSON file-based peristance for Events
 * 
 * {@literal @}Component Spring annotation instantiates a single instance of this
 * class and injects the instance into other classes as Event
 * 
 */
@Component
public class EventFileDAO implements EventFileDAO<Event> {
    private static final Logger LOG = Logger.getLogger(EventFileDAO.class.getName());
    Map<String,Event> Events;   // Provides a local cache of the Event objects
                                // so that we don't Event to read from the file
                                // each time
    private ObjectMapper objectMapper;  // Provides conversion between Event
                                        // objects and JSON text format written
                                        // to the file
    private String filename;    // Filename to read from and write to

    /**
     * Creates a Event File Data Access Object
     * 
     * @param filename Filename to read from and write to
     * @param objectMapper Provides JSON Object to/from Java Object serialization and deserialization
     * 
     * @throws IOException when file cannot be accessed or read from
     */
     //Is the ObjectMapper automatically inserted?
    public EventFileDAO(@Value("${events.file}") String filename,ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();  // load the Events from the file
    }

    /**
     * Generates an array of {@linkplain Event Events} from the tree map
     * 
     * @return  The array of {@link Event Events}, may be empty
     */
    private Event[] getCupboardArray() {
        return getCupboardArray(null);
    }

    /**
     * Generates an array of {@linkplain Event Events} from the tree map for any
     * {@linkplain Event Events} that contains the text specified by containsText
     * <br>
     * If containsText is null, the array contains all of the {@linkplain Event Events}
     * in the tree map
     * 
     * @return  The array of {@link Event Events}, may be empty
     */
    private Event[] getCupboardArray(String containsText) { // if containsText == null, no filter
        ArrayList<Event> EventArrayList = new ArrayList<>();

        for (Event Event : Events.values()) {
            if (containsText == null || Event.getName().contains(containsText)) {
                EventArrayList.add(Event);
            }
        }

        Event[] EventArray = new Event[EventArrayList.size()];
        EventArrayList.toArray(EventArray);
        return EventArray;
    }

    /**
     * Saves the {@linkplain Event Events} from the map into the file as an array of JSON objects
     * 
     * @return true if the {@link Event Events} were written successfully
     * 
     * @throws IOException when file cannot be accessed or written to
     */
    private boolean save() throws IOException {
        Event[] EventArray = getCupboardArray();
        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(new File(filename),EventArray);
        return true;
    }

    /**
     * Loads {@linkplain Event Events} from the JSON file into the map
     * <br>
     * 
     * @return true if the file was read successfully
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    private boolean load() throws IOException {
        Events = new TreeMap<>();
        String nextName = "";

        // Deserializes the JSON objects from the file into an array of Events
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file
        Event[] EventArray = objectMapper.readValue(new File(filename),Event[].class);

        // Add each Event to the tree map and keep track of the greatest id
        for (Event Event : EventArray) {
            Events.put(Event.getName(),Event);
            if (Event.getName().compareTo(nextName) > 0){
                nextName = Event.getName();
            }
        }
        return true;
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Event[] getDataArray() throws IOException  {
        synchronized(Events) {
            return getDataArray();
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Event[] searchDataArray(String containsText)throws IOException {
        synchronized(Events) {
            return searchDataArray(containsText);
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Event getData(String name) throws IOException  {
        synchronized(Events) {
            if (Events.containsKey(name))
                return Events.get(name);
            else
                return null;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Event createData(Event Event) throws IOException {
        synchronized(Events) {
            
            Event exists = getData(Event.getName());

            if(exists == null){
                Event newEvent = new Event(Event.getName(), Event.getDate(), Event.getTime());
                Events.put(newEvent.getName(),newEvent);
                save(); // may throw an IOException
                return newEvent;
            }else{
                return null;
            }
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Event updateData(Event Event) throws IOException {
        synchronized(Events) {
            if (Events.containsKey(Event.getName()) == false)
                return null;  // Event does not exist

            Events.put(Event.getName(),Event);
            save(); // may throw an IOException
            return Event;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public boolean deleteData(String name) throws IOException {
        synchronized(Events) {
            if (Events.containsKey(name)) {
                Events.remove(name);
                return save();
            }
            else
                return false;
        }
    }
}