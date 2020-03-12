package by.epam.booking.repository;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.exception.RepositoryException;
import by.epam.booking.specification.Specification;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The interface Data base repository.
 *
 * @param <T> the type parameter
 * @author Anton Vedenichev
 * @since 1.0
 */
public interface DataBaseRepository<T> {

    /** Save entity in database.
     *
     * @throws RepositoryException the repository exception if cannot execute sql request
     * @param user the user
     * @throws RepositoryException the repository exception
     * @throws SQLException        the sql exception
     */
    void add(T user) throws RepositoryException, SQLException;

    /**
     * Remove users from database
     *
     * @param user the user
     */
    void remove(T user);

    /**
     * Query results to be processed by the repository assistant.
     *
     * @param specification the specification
     * @return the result set
     * @throws RepositoryException the repository exception
     */
    ResultSet query(Specification specification) throws RepositoryException;

    /**
     * Close connection.
     *
     * @param connection the connection
     */
    default void closeConnection(Connection connection) {
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
    default void closeStatement(Statement statement) throws RepositoryException {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RepositoryException(e);
            }
        }
    }
}
