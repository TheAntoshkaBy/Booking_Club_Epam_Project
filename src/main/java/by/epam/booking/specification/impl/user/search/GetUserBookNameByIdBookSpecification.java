package by.epam.booking.specification.impl.user.search;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.entity.User;
import by.epam.booking.exception.ConnectionPoolException;
import by.epam.booking.exception.SpecificationException;
import by.epam.booking.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GetUserBookNameByIdBookSpecification implements Specification {

    private static Logger logger = LogManager.getLogger();
    private final String SQL_REQUEST = "SELECT b.name FROM "+ USER_TABLE +" JOIN Book b on u.bookId = b.idBook"+" WHERE u.login=?";
    private String login;

    public GetUserBookNameByIdBookSpecification(String login)
    {
        this.login = login;
    }

    @Override
    public PreparedStatement specify() throws SpecificationException {
        PreparedStatement statement = null;
        try {
            statement = ConnectionPool.getInstance().getConnection().prepareStatement(SQL_REQUEST);
            statement.setString(1,login);
        } catch (SQLException | ConnectionPoolException e) {
            throw new SpecificationException(e);
        }
        return statement;
    }

    @Override
    public boolean isUpdate() {
        return false;
    }
}
