package by.epam.booking.specification.impl.book;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.entity.Book;
import by.epam.booking.specification.Specification;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GetAllCommentsBook implements Specification {

    private int bookId;
    private final String SQL_REQUEST = "SELECT c.author, c.date,c.header, c.text FROM "+ BOOK_TABLE +
            " JOIN Comments c on b.idBook = c.bookId WHERE c.bookId=?";

    public GetAllCommentsBook(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public PreparedStatement specify() {
        PreparedStatement statement = null;
        try {
            statement = ConnectionPool.getInstance().getConnection().prepareStatement(SQL_REQUEST);
            statement.setInt(1,bookId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    @Override
    public boolean isUpdate() {
        return false;
    }
}
