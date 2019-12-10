package by.epam.booking.repository.assistant;

import by.epam.booking.connection.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class RepositoryHelper {
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
