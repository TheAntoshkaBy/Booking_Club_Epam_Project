package by.epam.booking.repository.assistant.book;

import by.epam.booking.exception.RepositoryException;
import by.epam.booking.repository.assistant.RepositoryHelper;
import by.epam.booking.repository.impl.BookRepository;
import by.epam.booking.specification.impl.book.SelectMaxIdBookSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetMaxId extends RepositoryHelper {

    private static Logger logger = LogManager.getLogger();

    public static Integer getMaxId() throws RepositoryException {
        Integer result = null;
        ResultSet resultBook = BookRepository.getInstance().query(new SelectMaxIdBookSpecification());

        try {
            try {
                while (resultBook.next()) {
                    result = resultBook.getInt(1);
                }
            } finally {
                closeConnection(resultBook.getStatement().getConnection());
                closeStatement(resultBook.getStatement());
            }
        }catch (SQLException e){
            logger.error(e);
            throw new RepositoryException(e);
        }

        return result;
    }
}
