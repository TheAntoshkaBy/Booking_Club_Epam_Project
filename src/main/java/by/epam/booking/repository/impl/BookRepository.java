package by.epam.booking.repository.impl;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.entity.Book;
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

public class BookRepository implements DataBaseRepository<Book> {

    private static Logger logger = LogManager.getLogger();
    private static final String SQL_INSERT_USER =
            "INSERT INTO Booking_Club.Book " +
                    "(count, author, name, description) VALUES (?,?,?,?)";
    private Statement statement;

    private static final BookRepository INSTANCE = new BookRepository();

    public static BookRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(Book book) throws RepositoryException, SQLException {
        PreparedStatement preparedStatement = null;
        try {

            preparedStatement = ConnectionPool.getInstance().getConnection().prepareStatement(SQL_INSERT_USER);
        } catch (SQLException | ConnectionPoolException e) {
            throw new RepositoryException(e);
        }
        try {
            preparedStatement.setInt(1, book.getCount());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getName());
            preparedStatement.setString(4, book.getDescription());
            preparedStatement.executeUpdate();
            closeConnection(preparedStatement.getConnection());
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }finally {
            closeConnection(preparedStatement.getConnection());
            closeStatement(preparedStatement);
        }
    }

    @Override
    public void remove(Book user) {

    }


    @Override
    public ResultSet query(Specification specification) throws RepositoryException {
        ResultSet resultSet = null;
        try {
            if(specification.isUpdate())
            {
                statement = specification.specify();
                RepositoryHelper.closeConnection(statement.getConnection());
            }else {
                resultSet = specification.specify().executeQuery();
            }
        } catch (SQLException | SpecificationException e) {
            throw new RepositoryException(e);
        }
        return resultSet;
    }
}
