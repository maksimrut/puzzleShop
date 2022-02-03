package com.rutkouski.puzzleshop.model.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Connection factory for creating {@link CustomConnectionPool}
 */
public class ConnectionFactory {
    static Logger logger = LogManager.getLogger();
    private static final String PROPERTY_FILE_PATH = "config/database.properties";
    private static final String PROPERTY_URL = "db_url";
    private static final String PROPERTY_DRIVER = "db_driver";
    private static final String PROPERTY_POOL_SIZE = "db_pool_size";
    private static final String PROPERTY_USER = "db_user";
    private static final String PROPERTY_PASSWORD = "db_password";
    private static final String DATABASE_URL;
    private static final String DATABASE_DRIVER;
    private static final String DATABASE_USER;
    private static final String DATABASE_PASSWORD;
    private static final int DEFAULT_POOL_SIZE = 8;
    private static final Properties properties = new Properties();
    static final int POOL_SIZE;

    static {
        InputStream propertyFileStream = ConnectionFactory.class.getClassLoader().getResourceAsStream(PROPERTY_FILE_PATH);
        try {
            properties.load(propertyFileStream);
            DATABASE_URL = properties.getProperty(PROPERTY_URL);
            DATABASE_DRIVER = properties.getProperty(PROPERTY_DRIVER);
            DATABASE_USER = properties.getProperty(PROPERTY_USER);
            DATABASE_PASSWORD = properties.getProperty(PROPERTY_PASSWORD);
            Class.forName(DATABASE_DRIVER);
        } catch (IOException e) {
            logger.fatal("Can not read data [{}] for database creating ", PROPERTY_FILE_PATH, e);
            throw new RuntimeException("Can not read data for database creating: ", e);
        } catch (ClassNotFoundException e) {
            logger.fatal("Unable to load application driver: {} was not found: {}", properties.getProperty(PROPERTY_DRIVER), e);
            throw new RuntimeException("Unable to load application driver: ", e);
        }
        int tempPoolSize;
        try {
            tempPoolSize = Integer.parseInt(properties.getProperty(PROPERTY_POOL_SIZE));
        } catch (NumberFormatException e) {
            logger.info("String does not contain a parsable integer for POOL_SIZE variable", e);
            tempPoolSize = DEFAULT_POOL_SIZE;
        }
        POOL_SIZE = tempPoolSize;
        logger.info("Database connection completed: URL - {}", DATABASE_URL);
    }

    private ConnectionFactory() {
    }

    static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
    }
}
