package com.epam.booking.connection;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.exception.ConnectionPoolException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolTest {

    private ConnectionPool connectionPool;

    @BeforeTest
    public void init() throws ConnectionPoolException {
        connectionPool = ConnectionPool.getInstance();
    }

    @Test
    public void testConnection() throws ConnectionPoolException {

        Connection firstConnection = connectionPool.getConnection();
        Connection secondConnection  = connectionPool.getConnection();
        Assert.assertEquals(ConnectionPool.getBusyConnections().size(), 2);
        connectionPool.returnConnection(firstConnection);
        connectionPool.returnConnection(secondConnection);
    }

    @Test
    public void testReturnConnection() throws ConnectionPoolException {
        Connection firstConnection = connectionPool.getConnection();
        Connection secondConnection  = connectionPool.getConnection();
        connectionPool.returnConnection(firstConnection);
        connectionPool.returnConnection(secondConnection);
        Assert.assertEquals(ConnectionPool.getBusyConnections().size(), 0);
    }

}
