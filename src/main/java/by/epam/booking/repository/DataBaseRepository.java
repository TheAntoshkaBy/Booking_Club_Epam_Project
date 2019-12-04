package by.epam.booking.repository;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.specification.Specification;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface DataBaseRepository<T> {

    void add(T user);
    void remove(T user);

    ResultSet query(Specification specification);

    default void closeConnection(Connection connection) {
        if (connection != null) {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    default void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
