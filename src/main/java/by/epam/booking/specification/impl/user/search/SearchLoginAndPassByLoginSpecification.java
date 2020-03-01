package by.epam.booking.specification.impl.user.search;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.exception.ConnectionPoolException;
import by.epam.booking.exception.SpecificationException;
import by.epam.booking.specification.Specification;
import by.epam.booking.entity.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SearchLoginAndPassByLoginSpecification implements Specification {
    private final String SQL_REQUEST = "SELECT login, password FROM "+ USER_TABLE +"  WHERE login=?";
    private User user;

    public SearchLoginAndPassByLoginSpecification(User user)
    {
        this.user = user;
    }

    @Override
    public PreparedStatement specify() throws SQLException, SpecificationException {
        PreparedStatement statement = null;
        try {
            statement = ConnectionPool.getInstance().getConnection().prepareStatement(SQL_REQUEST);
            statement.setString(1,user.getLogin());
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
