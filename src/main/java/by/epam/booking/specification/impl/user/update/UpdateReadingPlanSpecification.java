package by.epam.booking.specification.impl.user.update;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateReadingPlanSpecification implements Specification {
    private String login;
    private Integer newReadingPlanId;
    private String SQL_REQUEST = "UPDATE " + USER_TABLE +" SET redingPlanId WHERE login=?";
    private String NULL_SQL_REQUEST = "UPDATE "+USER_TABLE+" SET readingPlanId=NULL WHERE login=?";
    public UpdateReadingPlanSpecification(String login, Integer newReadingPlanId) {
        this.login = login;
        this.newReadingPlanId = newReadingPlanId;
    }

    public UpdateReadingPlanSpecification(String login) {
        this.login = login;
        this.newReadingPlanId = null;
    }

    @Override
    public PreparedStatement specify() {
        PreparedStatement statement = null;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            if(newReadingPlanId!=null){
                statement = connection.prepareStatement(SQL_REQUEST);
                statement.setInt(1,newReadingPlanId);
                statement.setString(2,login);
                statement.executeUpdate();
            }else {
                statement = connection.prepareStatement(NULL_SQL_REQUEST);
                statement.setString(1,login);
                statement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    @Override
    public boolean isUpdate() {
        return true;
    }
}
