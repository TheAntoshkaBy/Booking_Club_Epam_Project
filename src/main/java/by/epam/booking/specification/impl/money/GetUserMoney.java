package by.epam.booking.specification.impl.money;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GetUserMoney implements Specification {
    private final String SQL_REQUEST = "SELECT moneyBalance FROM "+ USER_TABLE +"  WHERE login=?";
    private String login;

    public GetUserMoney(String login)
    {
        this.login = login;
    }

    @Override
    public PreparedStatement specify() throws SQLException {
        PreparedStatement statement = null;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SQL_REQUEST);
            statement.setString(1,this.login);

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
