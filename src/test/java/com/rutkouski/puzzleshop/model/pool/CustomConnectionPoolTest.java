package com.rutkouski.puzzleshop.model.pool;

import com.rutkouski.puzzleshop.exception.DaoException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;

import static org.testng.Assert.*;

public class CustomConnectionPoolTest {
    CustomConnectionPool connectionPool;
    Connection connection;

    @BeforeClass
    public void before() {
        connectionPool = CustomConnectionPool.getInstance();

    }

    @Test
    public void testTakeConnection() {
        connection = connectionPool.takeConnection();
        assertNotNull(connection);
        connectionPool.releaseConnection(connection);
    }
}