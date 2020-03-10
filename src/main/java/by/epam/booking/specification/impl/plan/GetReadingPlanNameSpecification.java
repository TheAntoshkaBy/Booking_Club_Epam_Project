package by.epam.booking.specification.impl.plan;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.exception.ConnectionPoolException;
import by.epam.booking.exception.SpecificationException;
import by.epam.booking.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GetReadingPlanNameSpecification implements Specification {

    private final String SQL_REQUEST = "SELECT r.name FROM "+ READING_PLAN_TABLE + " WHERE r.idReadingPlan=?";
    private static Logger logger = LogManager.getLogger();
    private Integer readingPlanId;

    public GetReadingPlanNameSpecification(Integer id) {
        this.readingPlanId = id;
    }

    @Override
    public PreparedStatement specify() throws SpecificationException {
        PreparedStatement statement = null;
        try {
            statement = ConnectionPool.getInstance().getConnection().prepareStatement(SQL_REQUEST);
            statement.setInt(1,readingPlanId);
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new SpecificationException(e);
        }
        return statement;
    }

    @Override
    public boolean isUpdate() {
        return false;
    }
}
