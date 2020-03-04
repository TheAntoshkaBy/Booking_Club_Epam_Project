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

public class UpdateLoginByLoginSpecification implements Specification
{
    private static Logger logger = LogManager.getLogger();
    private String userLogin;
    private String newUserLogin;
    private String SQL_REQUEST = "UPDATE "+USER_TABLE+" SET login=? WHERE login=?";

    public UpdateLoginByLoginSpecification(String userLogin, String newUserLogin) {
        this.userLogin = userLogin;
        this.newUserLogin = newUserLogin;
    }

    @Override
    public PreparedStatement specify() throws SpecificationException {
        PreparedStatement statement = null;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_REQUEST);
            statement.setString(1,newUserLogin);
            statement.setString(2,userLogin);
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
