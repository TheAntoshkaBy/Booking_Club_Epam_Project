package by.epam.booking.repository.impl;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.repository.DataBaseRepository;
import by.epam.booking.specification.Specification;
import by.epam.booking.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepository implements DataBaseRepository<User> {

    private static final String SQL_INSERT_USER =
            "INSERT INTO Booking_Club.User " +
                    "(login, password, email, name, surname, role, isActive) VALUES (?,?,?,?,?,?,?)";
    private Statement statement;

    private static final UserRepository INSTANCE = new UserRepository();

    public static UserRepository getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public void add(User user) {
        PreparedStatement preparedStatement = null;
        try {

            preparedStatement = ConnectionPool.getInstance().getConnection().prepareStatement(SQL_INSERT_USER);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getName());
            preparedStatement.setString(5, user.getSurname());
            preparedStatement.setString(6, user.getRole().name());
            preparedStatement.setBoolean(7,false);


            preparedStatement.executeUpdate();
            closeConnection(preparedStatement.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                closeConnection(preparedStatement.getConnection());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeStatement(preparedStatement);
        }
    }

    @Override
    public void remove(User user) {

    }

    // FIXME: 02.12.2019 Спросить как лучше работать с обновлениями
    @Override
    public ResultSet query(Specification specification) {
        ResultSet resultSet = null;
        try {
            if(specification.isUpdate())
            {
                specification.specify().executeUpdate();
                statement= specification.specify();
            }else {
                resultSet = specification.specify().executeQuery();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public Statement getStatement() {
        return statement;
    }
}
