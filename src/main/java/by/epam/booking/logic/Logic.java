package by.epam.booking.logic;

import by.epam.booking.connection.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Logic {
    protected static void closeConnection(Connection connection) {
        if (connection != null) {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    protected static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
