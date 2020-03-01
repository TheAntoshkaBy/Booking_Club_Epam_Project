package by.epam.booking.specification.impl.user.search;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.exception.ConnectionPoolException;
import by.epam.booking.exception.SpecificationException;
import by.epam.booking.specification.Specification;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SearchUserByLoginSpecification implements Specification {

    private final String SQL_REQUEST = "SELECT u.name,r.name,r.idReadingPlan,u.login,u.surname,u.email,u.password,u.moneyBalance,u.isActive,u.role,b.name, u.bookId " +
            "FROM " + USER_TABLE +
            " LEFT JOIN Book b on u.bookId = b.idBook"+
            " LEFT JOIN ReadingPlan r on u.readingPlanId = r.idReadingPlan"+
            "  WHERE login=?";
    private String login;

    public SearchUserByLoginSpecification(String login)
    {
        this.login = login;
    }

    @Override
    public PreparedStatement specify() throws SQLException, SpecificationException {
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
