package by.epam.booking.specification.impl.book;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.specification.Specification;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GetBookImage implements Specification {
    private final String SQL_REQUEST = "SELECT book_image FROM "+ BOOK_TABLE +"  WHERE idBook=?";
    private Integer login;

    public GetBookImage(Integer login)
    {
        this.login = login;
    }

    @Override
    public PreparedStatement specify() throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = ConnectionPool.getInstance().getConnection().prepareStatement(SQL_REQUEST);
            statement.setInt(1,login);
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
