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

public class UpdateSurnameByLoginSpecification implements Specification {

    private String login;
    private String newSurname;
    private String SQL_REQUEST = "UPDATE "+USER_TABLE+" SET surname=? WHERE login=?";
    private static Logger logger = LogManager.getLogger();

    public UpdateSurnameByLoginSpecification(String login, String newSurname) {
        this.login = login;
        this.newSurname = newSurname;
    }

    @Override
    public PreparedStatement specify() throws SQLException, SpecificationException {
        PreparedStatement statement = null;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_REQUEST);
            statement.setString(1,newSurname);
            statement.setString(2,login);
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new SpecificationException(e);
        }
        return statement;
    }

    @Override
    public boolean isUpdate() {
        return true;
    }
}
