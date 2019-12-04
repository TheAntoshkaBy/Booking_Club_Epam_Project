package by.epam.booking.specification.impl.user.search;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.specification.Specification;
import by.epam.booking.entity.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SearchLoginAndPassByLogin implements Specification {
    private final String SQL_REQUEST = "SELECT login, password FROM "+ USER_TABLE +"  WHERE login=?";
    private User user;

    public SearchLoginAndPassByLogin(User user)
    {
        this.user = user;
    }

    @Override
    public PreparedStatement specify() throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = ConnectionPool.getInstance().getConnection().prepareStatement(SQL_REQUEST);
            statement.setString(1,user.getLogin());
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
