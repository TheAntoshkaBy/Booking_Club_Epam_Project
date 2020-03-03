package by.epam.booking.repository.impl;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.entity.User;
import by.epam.booking.exception.ConnectionPoolException;
import by.epam.booking.exception.RepositoryException;
import by.epam.booking.exception.SpecificationException;
import by.epam.booking.repository.DataBaseRepository;
import by.epam.booking.repository.assistant.RepositoryHelper;
import by.epam.booking.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepository implements DataBaseRepository<User> {

    private static Logger logger = LogManager.getLogger();
    private static final String SQL_INSERT_USER =
            "INSERT INTO Booking_Club.User " +
                    "(login, password, email, name, surname, role, isActive) VALUES (?,?,?,?,?,?,?)";
    private static final UserRepository INSTANCE = new UserRepository();
    private Statement statement;

    public static UserRepository getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public void add(User user) throws RepositoryException, SQLException {
        PreparedStatement preparedStatement = null;
        try {

            preparedStatement = ConnectionPool.getInstance().getConnection().prepareStatement(SQL_INSERT_USER);
        } catch (SQLException | ConnectionPoolException e) {
            throw new RepositoryException(e);
        }
        try {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getName());
            preparedStatement.setString(5, user.getSurname());
            preparedStatement.setString(6, user.getRole().name());
            preparedStatement.setBoolean(7, false);


            preparedStatement.executeUpdate();
            closeConnection(preparedStatement.getConnection());
        } catch (SQLException e) {
            throw new RepositoryException(e);
        } finally {

            closeConnection(preparedStatement.getConnection());
            closeStatement(preparedStatement);
        }
    }

    @Override
    public void remove(User user) {

    }

    @Override
    public ResultSet query(Specification specification) {
        ResultSet resultSet = null;
        try {
            if (specification.isUpdate()) {
                statement = specification.specify();
                RepositoryHelper.closeConnection(statement.getConnection());
            } else {
                resultSet = specification.specify().executeQuery();
            }

        } catch (SQLException | SpecificationException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public Statement getStatement() {
        return statement;
    }
}
