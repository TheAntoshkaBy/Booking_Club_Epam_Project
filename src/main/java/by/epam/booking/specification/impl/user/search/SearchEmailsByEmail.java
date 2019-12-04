package by.epam.booking.specification.impl.user.search;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.specification.Specification;
import by.epam.booking.entity.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SearchEmailsByEmail implements Specification {

    private final String SQL_REQUEST = "SELECT email FROM "+ USER_TABLE +"  WHERE email=?";
    private User user;

    public SearchEmailsByEmail(User user)
    {
        this.user = user;
    }

    @Override
    public PreparedStatement specify() throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = ConnectionPool.getInstance().getConnection().prepareStatement(SQL_REQUEST);
            statement.setString(1,user.getEmail());
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
