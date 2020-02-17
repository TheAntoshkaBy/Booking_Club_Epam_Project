package by.epam.booking.specification.impl.book.update;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteBook implements Specification {

    private Integer bookId;
    private String SQL_REQUEST = "DELETE FROM Booking_Club.Book" + " WHERE idBook=?";

    public DeleteBook(Integer bookId) {
        this.bookId = bookId;
    }

    @Override
    public PreparedStatement specify() {
        PreparedStatement statement = null;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();

            statement = connection.prepareStatement(SQL_REQUEST);
            statement.setInt(1, bookId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    @Override
    public boolean isUpdate() {
        return true;
    }
}
