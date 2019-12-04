package by.epam.booking.repository.impl;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.entity.Book;
import by.epam.booking.repository.DataBaseRepository;
import by.epam.booking.specification.Specification;
import by.epam.booking.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRepository implements DataBaseRepository<Book> {
    private static final String SQL_INSERT_USER =
            "INSERT INTO Booking_Club.Book " +
                    "(count, author, name, description) VALUES (?,?,?,?)";

    private static final UserRepository INSTANCE = new UserRepository();

    public static UserRepository getINSTANCE() {
        return INSTANCE;
    }

    // FIXME: 03.12.2019 вопрос по закрытиям конекшена
    @Override
    public void add(Book book) {
        PreparedStatement preparedStatement = null;
        try {

            preparedStatement = ConnectionPool.getInstance().getConnection().prepareStatement(SQL_INSERT_USER);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            preparedStatement.setInt(1, book.getCount());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getName());
            preparedStatement.setString(4, book.getDescription());


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
    public void remove(Book user) {

    }


    @Override
    public ResultSet query(Specification specification) {
        ResultSet resultSet = null;
        try {
            resultSet = specification.specify().executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
