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

public class SelectLibraryTableInfoByAuthorAndNameSpecification implements Specification {

    private static Logger logger = LogManager.getLogger();
    private final String SQL_REQUEST = "SELECT count FROM "+ BOOK_TABLE +"  WHERE name=? AND author=?";
    private Book book;

    public SelectLibraryTableInfoByAuthorAndNameSpecification(Book book)
    {
        this.book = book;
    }

    @Override
    public PreparedStatement specify() throws SQLException, SpecificationException {
        PreparedStatement statement = null;
        try {
            statement = ConnectionPool.getInstance().getConnection().prepareStatement(SQL_REQUEST);
            statement.setString(1,book.getName());
            statement.setString(2,book.getAuthor());
        } catch (SQLException | ConnectionPoolException e) {
            throw new SpecificationException(e);
        }
        return statement;
    }

    @Override
    public boolean isUpdate() {
        return false;
    }
}
