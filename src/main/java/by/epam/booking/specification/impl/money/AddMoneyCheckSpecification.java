package by.epam.booking.specification.impl.money;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.exception.ConnectionPoolException;
import by.epam.booking.exception.SpecificationException;
import by.epam.booking.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddMoneyCheckSpecification implements Specification {

    private Double money;
    private String login;
    private String type;
    private long date;
    private String SQL_REQUEST = "INSERT INTO Booking_Club.MoneyStory (moneyBalance, authorOperationLogin, typeOperation, date) VALUES (?,?,?,?); ";

    public AddMoneyCheckSpecification(Double money, String login, String type, long date) {
        this.money = money;
        this.login = login;
        this.type = type;
        this.date = date;
    }

    @Override
    public PreparedStatement specify() throws SQLException, SpecificationException {
        PreparedStatement statement = null;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SQL_REQUEST);
            statement.setDouble(1,money);
            statement.setString(2,login);
            statement.setString(3,type);
            statement.setLong(4,date);
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new SpecificationException(e);
        }
        return statement;
    }

    @Override
    public boolean isUpdate() {
        return false;
    }
}
