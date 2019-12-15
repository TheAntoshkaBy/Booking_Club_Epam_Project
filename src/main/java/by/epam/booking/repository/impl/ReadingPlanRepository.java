package by.epam.booking.repository.impl;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.entity.ReadingPlan;
import by.epam.booking.entity.User;
import by.epam.booking.repository.DataBaseRepository;
import by.epam.booking.repository.assistant.RepositoryHelper;
import by.epam.booking.specification.Specification;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReadingPlanRepository implements DataBaseRepository<ReadingPlan> {
    private static final String SQL_INSERT_USER =
            "INSERT INTO Booking_Club.ReadingPlan " +
                    "(name, description) VALUES (?,?)";
    private Statement statement;

    private static final ReadingPlanRepository INSTANCE = new ReadingPlanRepository();

    public static ReadingPlanRepository getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public void add(ReadingPlan readingPlan) {
        PreparedStatement preparedStatement = null;
        try {

            preparedStatement = ConnectionPool.getInstance().getConnection().prepareStatement(SQL_INSERT_USER);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            preparedStatement.setString(1, readingPlan.getName());
            preparedStatement.setString(2, readingPlan.getDescription());


            preparedStatement.executeUpdate();
            closeConnection(preparedStatement.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                closeConnection(preparedStatement.getConnection());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeStatement(preparedStatement);
        }
    }

    @Override
    public void remove(ReadingPlan readingPlan) {

    }

    @Override
    public ResultSet query(Specification specification) {
        ResultSet resultSet = null;
        try {
            if(specification.isUpdate())
            {
                statement =specification.specify();
                RepositoryHelper.closeConnection(statement.getConnection());
            }else {
                resultSet = specification.specify().executeQuery();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public Statement getStatement() {
        return statement;
    }
}
