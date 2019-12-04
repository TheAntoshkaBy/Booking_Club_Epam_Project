package by.epam.booking.specification.impl.book;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.specification.Specification;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectMaxIdBook implements Specification {

    private final String SQL_REQUEST = "SELECT idBook FROM "+ BOOK_TABLE + " ORDER BY idBook DESC LIMIT 1";


    @Override
    public PreparedStatement specify() throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = ConnectionPool.getInstance().getConnection().prepareStatement(SQL_REQUEST);
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
