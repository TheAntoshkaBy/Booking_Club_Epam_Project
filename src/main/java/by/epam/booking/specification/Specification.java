package by.epam.booking.specification;

import by.epam.booking.connection.ConnectionPool;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public interface Specification {

    String USER_TABLE = "Booking_Club.User";
    String BOOK_TABLE = "Book";
    PreparedStatement specify() throws SQLException;
    boolean isUpdate();
}
