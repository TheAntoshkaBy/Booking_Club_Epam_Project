package by.epam.booking.specification.impl.user.update;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.exception.ConnectionPoolException;
import by.epam.booking.exception.SpecificationException;
import by.epam.booking.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateBookIdSpecification implements Specification {

    private static Logger logger = LogManager.getLogger();
    private String login;
    private Integer newBookId;
    private String SQL_REQUEST = "UPDATE " + USER_TABLE +" SET bookId=? WHERE login=?";
    private String NULL_SQL_REQUEST = "UPDATE "+USER_TABLE+" SET bookId=NULL WHERE login=?";
    public UpdateBookIdSpecification(String login, Integer newBookId) {
        this.login = login;
        this.newBookId = newBookId;
    }

    @Override
    public PreparedStatement specify() throws SpecificationException {
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
                statement.executeUpdate();
            }

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
