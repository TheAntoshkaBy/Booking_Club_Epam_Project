package by.epam.booking.specification.impl.book;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.entity.Book;
import by.epam.booking.specification.Specification;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectBookById implements Specification {

    private final String SQL_REQUEST = "SELECT name,author,count,idBook,description FROM "+ BOOK_TABLE + " WHERE idBook=?";
    private Integer bookId;

    public SelectBookById(Integer id) {
        this.bookId = id;
    }

    @Override
    public PreparedStatement specify() throws SQLException {
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
