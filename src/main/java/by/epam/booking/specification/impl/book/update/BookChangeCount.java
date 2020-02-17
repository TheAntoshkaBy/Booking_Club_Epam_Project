package by.epam.booking.specification.impl.book.update;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookChangeCount implements Specification {
    private Integer id;
    private Integer count;
    private String SQL_REQUEST = "UPDATE "+BOOK_TABLE+" SET count=? WHERE idBook=?";

    public BookChangeCount(Integer id, Integer count) {
        this.id = id;
        this.count = count;
    }

    @Override
    public PreparedStatement specify() throws SQLException {
        PreparedStatement statement = null;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_REQUEST);
            statement.setInt(1,count);
            statement.setInt(2,id);
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
