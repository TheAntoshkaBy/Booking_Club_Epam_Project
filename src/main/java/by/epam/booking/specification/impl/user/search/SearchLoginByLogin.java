package by.epam.booking.specification.impl.user.search;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.specification.Specification;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SearchLoginByLogin implements Specification {
        private final String SQL_REQUEST = "SELECT login FROM "+ USER_TABLE +"  WHERE login=?";
        private String login;

        public SearchLoginByLogin(String login)
        {
            this.login = login;
        }

        @Override
        public PreparedStatement specify() throws SQLException {
            PreparedStatement statement = null;
            try {
                statement = ConnectionPool.getInstance().getConnection().prepareStatement(SQL_REQUEST);
                statement.setString(1,this.login);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return statement;
        }

    @Override
    public boolean isUpdate() {
        return false;
    }
}
