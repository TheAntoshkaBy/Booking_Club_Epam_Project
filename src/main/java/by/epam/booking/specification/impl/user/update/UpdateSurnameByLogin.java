package by.epam.booking.specification.impl.user.update;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateSurnameByLogin implements Specification {
    private String login;
    private String newSurname;
    private String SQL_REQUEST = "UPDATE "+USER_TABLE+" SET surname=? WHERE login=?";

    public UpdateSurnameByLogin(String login, String newSurname) {
        this.login = login;
        this.newSurname = newSurname;
    }

    @Override
    public PreparedStatement specify() throws SQLException {
        PreparedStatement statement = null;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_REQUEST);
            statement.setString(1,newSurname);
            statement.setString(2,login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    @Override
    public boolean isUpdate() {
        return true;
    }
}
