package by.epam.booking.specification.impl.money;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.specification.Specification;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GetAllBalanceSpecification implements Specification {
    private int bookId;
    private final String SQL_REQUEST = "SELECT idOperation, moneyBalance, authorOperationLogin, typeOperation, date FROM "+ "Booking_Club.MoneyStory";

    public GetAllBalanceSpecification() {
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
