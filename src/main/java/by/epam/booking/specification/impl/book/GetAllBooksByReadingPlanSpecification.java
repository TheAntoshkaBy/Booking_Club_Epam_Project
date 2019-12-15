package by.epam.booking.specification.impl.book;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.entity.Book;
import by.epam.booking.specification.Specification;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GetAllBooksByReadingPlanSpecification implements Specification {
    private final String SQL_REQUEST = "SELECT b.name, b.description,b.author, b.count, b.idBook FROM Booking_Club.PlanPosition p " +
            "JOIN Booking_Club.Book b ON p.bookId=b.idBook" +
            " WHERE p.planId=?";

    private int planId;

    public GetAllBooksByReadingPlanSpecification(int planId) {
        this.planId = planId;
    }

    @Override
    public PreparedStatement specify() {
        PreparedStatement statement = null;
        try {
            statement = ConnectionPool.getInstance().getConnection().prepareStatement(SQL_REQUEST);
            statement.setInt(1,planId);
        } catch (SQLException e) {
            e.printStackTrace();
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
