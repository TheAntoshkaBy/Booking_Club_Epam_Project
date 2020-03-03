package by.epam.booking.specification.impl.book.update;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.exception.ConnectionPoolException;
import by.epam.booking.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateBookNameByIdSpecification implements Specification {

    private static Logger logger = LogManager.getLogger();
    private Integer id;
    private String newName;
    private String SQL_REQUEST = "UPDATE "+BOOK_TABLE+" SET name=? WHERE idBook=?";

    public UpdateBookNameByIdSpecification(Integer id, String newName) {
        this.id = id;
        this.newName = newName;
    }

    @Override
    public PreparedStatement specify() throws SQLException {
        PreparedStatement statement = null;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_REQUEST);
            statement.setString(1,newName);
            statement.setInt(2,id);
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }
        return statement;
    }

    @Override
    public boolean isUpdate() {
        return true;
    }
}
