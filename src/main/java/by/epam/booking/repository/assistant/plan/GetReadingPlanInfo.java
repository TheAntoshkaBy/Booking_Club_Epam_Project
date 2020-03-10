package by.epam.booking.repository.assistant.plan;

import by.epam.booking.entity.Book;
import by.epam.booking.entity.ReadingPlan;
import by.epam.booking.exception.RepositoryException;
import by.epam.booking.repository.assistant.RepositoryHelper;
import by.epam.booking.repository.assistant.book.GetAllBooks;
import by.epam.booking.repository.impl.BookRepository;
import by.epam.booking.specification.Specification;
import by.epam.booking.specification.impl.plan.GetAllReadingPlansSpecification;
import by.epam.booking.specification.impl.plan.GetReadingPlanNameSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetReadingPlanInfo extends RepositoryHelper {

    private static Logger logger = LogManager.getLogger();

    public static String getName(Integer id) throws RepositoryException {
        Specification specification = new GetReadingPlanNameSpecification(id);
        ResultSet planSet = BookRepository.getInstance().query(specification);
        String name = null;
        try {
            try {
                while (planSet.next()) {
                    name = planSet.getString("r.name");
                }
            } finally {
                closeConnection(planSet.getStatement().getConnection());
                closeStatement(planSet.getStatement());
            }
        }catch (SQLException e){
            logger.error(e);
            throw new RepositoryException(e);
        }

        return name;
    }
}
