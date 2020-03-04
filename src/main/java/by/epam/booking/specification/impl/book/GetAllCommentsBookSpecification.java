package by.epam.booking.specification.impl.book;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.entity.Book;
import by.epam.booking.exception.ConnectionPoolException;
import by.epam.booking.exception.SpecificationException;
import by.epam.booking.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GetAllCommentsBookSpecification implements Specification {

    private static Logger logger = LogManager.getLogger();
    private int bookId;
    private final String SQL_REQUEST = "SELECT c.author, c.date,c.header, c.text FROM "+ BOOK_TABLE +
            " JOIN Comments c on b.idBook = c.bookId WHERE c.bookId=?";

    public GetAllCommentsBookSpecification(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public PreparedStatement specify() throws SpecificationException {
        PreparedStatement statement = null;
        try {
            statement = ConnectionPool.getInstance().getConnection().prepareStatement(SQL_REQUEST);
            statement.setInt(1,bookId);
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new SpecificationException(e);
        }
        return statement;
    }

    @Override
    public boolean isUpdate() {
        return false;
    }
}
