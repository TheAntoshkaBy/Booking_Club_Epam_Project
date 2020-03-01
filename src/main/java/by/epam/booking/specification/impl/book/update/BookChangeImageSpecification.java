package by.epam.booking.specification.impl.book.update;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.exception.ConnectionPoolException;
import by.epam.booking.exception.SpecificationException;
import by.epam.booking.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookChangeImageSpecification implements Specification {

    private Integer bookId;
    private String bookImage;
    private String SQL_REQUEST = "UPDATE "+BOOK_TABLE+" SET book_image=? WHERE idBook=?";

    public BookChangeImageSpecification(Integer bookId, String bookImage) {
        this.bookId = bookId;
        this.bookImage = bookImage;
    }

    @Override
    public PreparedStatement specify() throws SQLException, SpecificationException {
        PreparedStatement statement = null;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_REQUEST);
            statement.setString(1,bookImage);
            statement.setInt(2,bookId);
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new SpecificationException(e);
        }
        return statement;
    }

    @Override
    public boolean isUpdate() {
        return true;
    }
}
