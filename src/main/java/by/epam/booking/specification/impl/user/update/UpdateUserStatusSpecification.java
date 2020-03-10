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

public class UpdateUserStatusSpecification implements Specification {
    private String login;
    private int status;
    private String SQL_REQUEST = "UPDATE "+USER_TABLE+" SET isActive=? WHERE login=?";
    private static Logger logger = LogManager.getLogger();

    public UpdateUserStatusSpecification(String login, Boolean status) {
        this.login = login;
        if(status){
            this.status = 1;
        }else {
            this.status = 0;
        }
    }

    @Override
    public PreparedStatement specify() throws SpecificationException {
        PreparedStatement statement = null;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_REQUEST);
            statement.setInt(1,status);
            statement.setString(2,login);
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
