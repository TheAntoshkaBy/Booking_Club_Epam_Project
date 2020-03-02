package by.epam.booking.specification.impl.user.search;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.exception.ConnectionPoolException;
import by.epam.booking.exception.SpecificationException;
import by.epam.booking.specification.Specification;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SearchLoginByLoginSpecification implements Specification {
        private final String SQL_REQUEST = "SELECT login FROM "+ USER_TABLE +"  WHERE login=?";
        private String login;

        public SearchLoginByLoginSpecification(String login)
        {
            this.login = login;
        }

        @Override
        public PreparedStatement specify() throws SQLException, SpecificationException {
            PreparedStatement statement = null;
            try {
                statement = ConnectionPool.getInstance().getConnection().prepareStatement(SQL_REQUEST);
                statement.setString(1,this.login);
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