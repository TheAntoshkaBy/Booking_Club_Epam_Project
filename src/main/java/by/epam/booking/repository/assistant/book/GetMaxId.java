package by.epam.booking.repository.assistant.book;

import by.epam.booking.exception.RepositoryException;
import by.epam.booking.repository.assistant.RepositoryHelper;
import by.epam.booking.repository.impl.BookRepository;
import by.epam.booking.specification.impl.book.SelectMaxIdBookSpecification;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetMaxId extends RepositoryHelper {
    public static Integer getMaxId() throws SQLException, RepositoryException {
        Integer result = null;
        ResultSet resultBook = BookRepository.getInstance().query(new SelectMaxIdBookSpecification());
        try {
            while (resultBook.next()) {
                result = resultBook.getInt(1);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        } finally {
            closeConnection(resultBook.getStatement().getConnection());
            closeStatement(resultBook.getStatement());
        }

        return result;
    }
}
