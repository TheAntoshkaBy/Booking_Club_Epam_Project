package by.epam.booking.repository;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.exception.RepositoryException;
import by.epam.booking.specification.Specification;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface DataBaseRepository<T> {

    void add(T user) throws RepositoryException, SQLException;
    void remove(T user);

    ResultSet query(Specification specification) throws RepositoryException;

    default void closeConnection(Connection connection) {
        if (connection != null) {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

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
