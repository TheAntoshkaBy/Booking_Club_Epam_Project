package by.epam.booking.repository.impl;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.entity.Balance;
import by.epam.booking.entity.ReadingPlan;
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

public class MoneyStoreRepository implements DataBaseRepository<Balance> {

    private PreparedStatement statement;
    private static final UserRepository INSTANCE = new UserRepository();
    private static Logger logger = LogManager.getLogger();

    public static UserRepository getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public void add(Balance user) {

    }

    @Override
    public void remove(Balance user) {

    }

    @Override
    public ResultSet query(Specification specification) throws RepositoryException {
        ResultSet resultSet = null;
        try {
            if(specification.isUpdate())
            {
                statement = specification.specify();
                RepositoryHelper.closeConnection(statement.getConnection());
            }else {
                resultSet = specification.specify().executeQuery();
            }

        } catch (SQLException | SpecificationException e) {
            throw new RepositoryException(e);
        }
        return resultSet;
    }
}
