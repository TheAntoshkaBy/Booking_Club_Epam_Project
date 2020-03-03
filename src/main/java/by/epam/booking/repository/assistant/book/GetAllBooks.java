package by.epam.booking.repository.assistant.book;

import by.epam.booking.entity.Book;
import by.epam.booking.exception.RepositoryException;
import by.epam.booking.repository.assistant.RepositoryHelper;
import by.epam.booking.repository.impl.BookRepository;
import by.epam.booking.specification.impl.book.GetAllBooksByReadingPlanSpecification;
import by.epam.booking.specification.impl.book.SelectAllBooksForTableSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetAllBooks extends RepositoryHelper {

    private static Logger logger = LogManager.getLogger();

    private static ArrayList<Book> books;

    static {
        books = new ArrayList<Book>();
    }

    public static ArrayList<Book> getAllBooks() throws RepositoryException {
        ResultSet bookSet = BookRepository.getInstance().query(new SelectAllBooksForTableSpecification());
        Book book;
        books.clear();
        try {
            while (bookSet.next()) {
                book = new Book();
                book.setAuthor(bookSet.getString("author"));
                book.setName(bookSet.getString("name"));
                book.setCount(bookSet.getInt("count"));
                book.setId(bookSet.getInt("idBook"));
                book.setDescription(bookSet.getString("description"));
                books.add(book);
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

    public static ArrayList<Book> getAllBooksInPlan(int planId) throws RepositoryException, SQLException {
        ResultSet bookSet = BookRepository.getInstance().query(new GetAllBooksByReadingPlanSpecification(planId));
        Book book;
        books.clear();
        try {
            while (bookSet.next()) {
                book = new Book();
                book.setAuthor(bookSet.getString("author"));
                book.setName(bookSet.getString("name"));
                book.setCount(bookSet.getInt("count"));
                book.setId(bookSet.getInt("idBook"));
                book.setDescription(bookSet.getString("description"));
                books.add(book);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        } finally {
            closeConnection(bookSet.getStatement().getConnection());
            closeStatement(bookSet.getStatement());
        }

        return books;
    }

    public static ArrayList<Book> getBooks() {
        return books;
    }
}
