package by.epam.booking.specification;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.exception.SpecificationException;

import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * The interface Specification.
 * @author Anton Vedenichev
 * @since 1.0
 */
public interface Specification {

    String USER_TABLE = "Booking_Club.User u";
    String BOOK_TABLE = "Booking_Club.Book b";
    String READING_PLAN_TABLE = "Booking_Club.ReadingPlan r";
    String BOOK_COMPLETED = "Booking_Club.Book_Complitor";

    /**
     * Specify prepared statement.
     *
     * @return the prepared statement
     * @throws SpecificationException the specification exception
     */
    PreparedStatement specify() throws SpecificationException;

    /**
     * A method due to which we understand
     * what operation is happening (receiving or changing data).
     *
     * @return the boolean
     */
    boolean isUpdate();
}
