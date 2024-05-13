package org.capital.gains.service;

import java.io.IOException;
import java.util.Properties;

import static org.capital.gains.util.MessagesContants.PROPERTIES_LOAD_ERROR;

/**
 * This class provides functionality to read properties from a properties file.
 */
public class PropertiesReaderService {

    private final Properties properties;

    /**
     * Constructs a new PropertiesReaderService and loads the properties from the "application.properties" file.
     * Throws an IllegalStateException if there's an error loading the properties file.
     */
    public PropertiesReaderService() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            throw new IllegalStateException(PROPERTIES_LOAD_ERROR, e);
        }
    }

    /**
     * Retrieves the value of the property associated with the specified key.
     *
     * @param key the key of the property to retrieve
     * @return the value of the property, or null if the key is not found
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

}
