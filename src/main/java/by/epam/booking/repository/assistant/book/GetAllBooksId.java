package by.epam.booking.repository.assistant.book;

import by.epam.booking.entity.Book;
import by.epam.booking.exception.RepositoryException;
import by.epam.booking.repository.assistant.RepositoryHelper;
import by.epam.booking.repository.impl.BookRepository;
import by.epam.booking.specification.impl.book.GetAllBooksIdSpecification;
import by.epam.booking.specification.impl.book.SelectAllBooksForTableSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetAllBooksId extends RepositoryHelper {

    private static Logger logger = LogManager.getLogger();

    private static ArrayList<Integer> books;

    static {
        books = new ArrayList<Integer>();
    }

    public static ArrayList<Integer> getAllBooksId() throws RepositoryException {
        ResultSet bookSet = BookRepository.getInstance().query(new GetAllBooksIdSpecification());
        Book book;
        books.clear();
        try {
            while (bookSet.next()) {
                books.add(bookSet.getInt("idBook"));
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        } finally {
            try {
                closeConnection(bookSet.getStatement().getConnection());
                closeStatement(bookSet.getStatement());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return books;
    }

}
