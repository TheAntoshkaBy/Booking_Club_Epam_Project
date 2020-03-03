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

public class GetAllBooksByReadingPlanSpecification implements Specification {

    private static Logger logger = LogManager.getLogger();
    private final String SQL_REQUEST = "SELECT b.name, b.description,b.author, b.count, b.idBook FROM Booking_Club.PlanPosition p " +
            "JOIN Booking_Club.Book b ON p.bookId=b.idBook" +
            " WHERE p.planId=?";

    private int planId;

    public GetAllBooksByReadingPlanSpecification(int planId) {
        this.planId = planId;
    }

    @Override
    public PreparedStatement specify() throws SpecificationException {
        PreparedStatement statement = null;
        try {
            statement = ConnectionPool.getInstance().getConnection().prepareStatement(SQL_REQUEST);
            statement.setInt(1,planId);
        } catch (SQLException | ConnectionPoolException e) {
            throw new SpecificationException(e);
        }
        return statement;
    }

    @Override
    public boolean isUpdate() {
        return false;
    }

    public int getPlanId() {
        return planId;
    }
}
