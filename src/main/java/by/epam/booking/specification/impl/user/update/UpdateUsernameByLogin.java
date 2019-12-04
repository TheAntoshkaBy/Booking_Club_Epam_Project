package by.epam.booking.specification.impl.user.update;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.entity.User;
import by.epam.booking.specification.Specification;

import javax.sql.rowset.serial.SerialStruct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateUsernameByLogin implements Specification {

    private String login;
    private String newName;
    private String SQL_REQUEST = "UPDATE "+USER_TABLE+" SET name=? WHERE login=?";

    public UpdateUsernameByLogin(String login, String newName) {
        this.login = login;
        this.newName = newName;
    }

    @Override
    public PreparedStatement specify() {
        PreparedStatement statement = null;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_REQUEST);
            statement.setString(1,newName);
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
