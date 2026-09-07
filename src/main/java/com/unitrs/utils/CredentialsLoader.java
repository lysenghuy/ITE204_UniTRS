package com.unitrs.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CredentialsLoader {

    private static final Logger LOGGER = Logger.getLogger(CredentialsLoader.class.getName());
    private static final String FILE_NAME = "db.properties";

    public static Properties loadProperties() {
        Properties properties = new Properties();

        try (InputStream input = CredentialsLoader.class.getClassLoader().getResourceAsStream(FILE_NAME)) {
            if (input == null) {
                throw new RuntimeException("Unable to find " + FILE_NAME + " in the classpath.");
            }
            properties.load(input);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Unable to load properties file", e);
            throw new RuntimeException("Unable to load properties file", e);
        }

        return properties;
    }
}
