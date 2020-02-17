package by.epam.booking.specification.impl.user.update;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteBookFromCompletedList implements Specification {
    private String login;
    private Integer newBookId;
    private String SQL_REQUEST = "DELETE FROM " + BOOK_COMPLETED + " WHERE userLogin=? AND idBook=?";

    public DeleteBookFromCompletedList(String login, Integer newBookId) {
        this.login = login;
        this.newBookId = newBookId;
    }

    @Override
    public PreparedStatement specify() {
        PreparedStatement statement = null;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();

            statement = connection.prepareStatement(SQL_REQUEST);
            statement.setInt(2, newBookId);
            statement.setString(1, login);
            statement.executeUpdate();

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
