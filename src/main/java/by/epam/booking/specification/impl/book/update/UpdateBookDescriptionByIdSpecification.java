package by.epam.booking.specification.impl.book.update;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.exception.ConnectionPoolException;
import by.epam.booking.exception.SpecificationException;
import by.epam.booking.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateBookDescriptionByIdSpecification implements Specification {

    private static Logger logger = LogManager.getLogger();
    private Integer id;
    private String description;
    private String SQL_REQUEST = "UPDATE "+BOOK_TABLE+" SET description=? WHERE idBook=?";

    public UpdateBookDescriptionByIdSpecification(Integer id, String newDesc) {
        this.id = id;
        this.description = newDesc;
    }

    @Override
    public PreparedStatement specify() throws SpecificationException {
        PreparedStatement statement = null;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_REQUEST);
            statement.setString(1,description);
            statement.setInt(2,id);
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new SpecificationException(e);
        }
        return statement;
    }

    @Override
    public boolean isUpdate() {
        return true;
    }
}
