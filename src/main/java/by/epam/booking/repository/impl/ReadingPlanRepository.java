package by.epam.booking.repository.impl;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.entity.ReadingPlan;
import by.epam.booking.exception.ConnectionPoolException;
import by.epam.booking.exception.RepositoryException;
import by.epam.booking.exception.SpecificationException;
import by.epam.booking.repository.DataBaseRepository;
import by.epam.booking.repository.assistant.RepositoryHelper;
import by.epam.booking.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReadingPlanRepository implements DataBaseRepository<ReadingPlan> {

    private static Logger logger = LogManager.getLogger();
    private static final String SQL_INSERT_USER =
            "INSERT INTO Booking_Club.ReadingPlan " +
                    "(name, description) VALUES (?,?)";
    private static final ReadingPlanRepository INSTANCE = new ReadingPlanRepository();
    private Statement statement;

    public static ReadingPlanRepository getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public void add(ReadingPlan readingPlan) throws RepositoryException {
        PreparedStatement preparedStatement = null;

        try {
            try {
                preparedStatement = ConnectionPool.getInstance().getConnection().prepareStatement(SQL_INSERT_USER);
                preparedStatement.setString(1, readingPlan.getName());
                preparedStatement.setString(2, readingPlan.getDescription());
                preparedStatement.executeUpdate();
                closeConnection(preparedStatement.getConnection());
            } finally {
                assert preparedStatement != null;
                closeConnection(preparedStatement.getConnection());
                closeStatement(preparedStatement);
            }
        }catch (SQLException | ConnectionPoolException e){
            logger.error(e);
            throw new RepositoryException(e);
        }



    }

    @Override
    public void remove(ReadingPlan readingPlan) {

    }

    @Override
    public ResultSet query(Specification specification) throws RepositoryException {
        ResultSet resultSet = null;
        try {
            if (specification.isUpdate()) {
                statement = specification.specify();
                RepositoryHelper.closeConnection(statement.getConnection());
            } else {
                resultSet = specification.specify().executeQuery();
            }

        } catch (SQLException | SpecificationException e) {
            throw new RepositoryException(e);
        }
        return resultSet;
    }

    public Statement getStatement() {
        return statement;
    }
}
