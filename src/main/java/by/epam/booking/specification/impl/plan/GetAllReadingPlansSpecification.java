package by.epam.booking.specification.impl.plan;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.specification.Specification;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GetAllReadingPlansSpecification implements Specification {
    private int bookId;
    private final String SQL_REQUEST = "SELECT r.name, r.description, r.idReadingPlan FROM "+ READING_PLAN_TABLE;

    public GetAllReadingPlansSpecification() {
    }

    @Override
    public PreparedStatement specify() {
        PreparedStatement statement = null;
        try {
            statement = ConnectionPool.getInstance().getConnection().prepareStatement(SQL_REQUEST);
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
