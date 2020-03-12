package by.epam.booking.repository.assistant;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.exception.RepositoryException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The type Repository helper.
 * Provides the interface necessary for correct work with Database.
 * @author Anton Vedenichev
 * @since 1.0
 */
public abstract class RepositoryHelper {
    /**
     * Close connection.
     *
     * @param connection the connection
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    /**
     * Close statement.
     *
     * @param statement the statement
     * @throws RepositoryException the repository exception
     */
    public static void closeStatement(Statement statement) throws RepositoryException {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RepositoryException(e);
            }
        }
    }
}
