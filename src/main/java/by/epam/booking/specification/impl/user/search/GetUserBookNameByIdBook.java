package by.epam.booking.specification.impl.user.search;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.entity.User;
import by.epam.booking.specification.Specification;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GetUserBookNameByIdBook implements Specification {
    private final String SQL_REQUEST = "SELECT b.name FROM "+ USER_TABLE+" u " +" JOIN Book b on u.bookId = b.idBook"+" WHERE u.login=?";
    private String login;

    public GetUserBookNameByIdBook(String login)
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
