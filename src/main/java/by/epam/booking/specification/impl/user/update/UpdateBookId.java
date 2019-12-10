package by.epam.booking.specification.impl.user.update;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateBookId implements Specification {
    private String login;
    private Integer newBookId;
    private String SQL_REQUEST = "UPDATE " + USER_TABLE +" SET bookId=? WHERE login=?";
    private String NULL_SQL_REQUEST = "UPDATE "+USER_TABLE+" SET bookId=NULL WHERE login=?";
    public UpdateBookId(String login, Integer newBookId) {
        this.login = login;
        this.newBookId = newBookId;
    }

    @Override
    public PreparedStatement specify() {
        PreparedStatement statement = null;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            if(newBookId!=null){
                statement = connection.prepareStatement(SQL_REQUEST);
                statement.setInt(1,newBookId);
                statement.setString(2,login);
                statement.executeUpdate();
            }else {
                statement = connection.prepareStatement(NULL_SQL_REQUEST);
                statement.setString(1,login);
            }

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
