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
                LOGGER.warning("Unable to find " + FILE_NAME + " in the classpath. Relying on Environment Variables.");
            } else {
                properties.load(input);
            }
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Unable to load properties file", e);
        }

        if (System.getenv("DB_URL") != null) {
            properties.setProperty("db.url", System.getenv("DB_URL"));
        }
        if (System.getenv("DB_USER") != null) {
            properties.setProperty("db.user", System.getenv("DB_USER"));
        }
        if (System.getenv("DB_PASSWORD") != null) {
            properties.setProperty("db.password", System.getenv("DB_PASSWORD"));
        }

        if (!properties.containsKey("db.url")) {
             throw new RuntimeException("No Database Configuration Found. Set DB_URL or provide db.properties");
        }

        return properties;
    }
}
