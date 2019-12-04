package by.epam.booking.specification.impl.book;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.entity.Book;
import by.epam.booking.specification.Specification;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectLibraryTableInfoByAuthorAndName implements Specification {
    private final String SQL_REQUEST = "SELECT count FROM "+ BOOK_TABLE +"  WHERE name=? AND author=?";
    private Book book;

    public SelectLibraryTableInfoByAuthorAndName(Book book)
    {
        this.book = book;
    }

    @Override
    public PreparedStatement specify() throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = ConnectionPool.getInstance().getConnection().prepareStatement(SQL_REQUEST);
            statement.setString(1,book.getName());
            statement.setString(2,book.getAuthor());
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
