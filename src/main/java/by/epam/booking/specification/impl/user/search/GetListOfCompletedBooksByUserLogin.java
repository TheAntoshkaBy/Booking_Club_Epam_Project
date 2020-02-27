package by.epam.booking.specification.impl.user.search;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.specification.Specification;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GetListOfCompletedBooksByUserLogin implements Specification {
    private final String SQL_REQUEST = "SELECT bkm.idBook FROM "+ USER_TABLE +" JOIN Book_Complitor bkm on u.login = bkm.userLogin"+" WHERE u.login=?";
    private String login;

    public GetListOfCompletedBooksByUserLogin(String login)
    {
        this.login = login;
    }

    @Override
    public PreparedStatement specify() {
        PreparedStatement statement = null;
        try {
            statement = ConnectionPool.getInstance().getConnection().prepareStatement(SQL_REQUEST);
            statement.setString(1,login);
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