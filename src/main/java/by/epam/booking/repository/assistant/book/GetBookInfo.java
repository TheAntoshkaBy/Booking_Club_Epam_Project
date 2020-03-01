package by.epam.booking.repository.assistant.book;

import by.epam.booking.entity.Book;
import by.epam.booking.exception.RepositoryException;
import by.epam.booking.repository.assistant.RepositoryHelper;
import by.epam.booking.repository.impl.BookRepository;
import by.epam.booking.specification.impl.book.GetBookImageSpecification;
import by.epam.booking.specification.impl.book.SelectBookByIdSpecification;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetBookInfo extends RepositoryHelper {
    public static boolean getBookById(Book book) throws RepositoryException, SQLException {
        ResultSet resultBook = BookRepository.getInstance().query(new SelectBookByIdSpecification(book.getId()));
        try {
            while (resultBook.next()) {
                book.setId(resultBook.getInt("idBook"));
                book.setCount(resultBook.getInt("count"));
                book.setAuthor(resultBook.getString("author"));
                book.setName(resultBook.getString("name"));
                book.setDescription(resultBook.getString("description"));
            }
            if (book.getName() == null) {
                return false;
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        } finally {
            closeConnection(resultBook.getStatement().getConnection());
            closeStatement(resultBook.getStatement());
        }
        return true;
    }

    public static String getBookImageById(Book book) throws RepositoryException, SQLException {
        ResultSet resultBook = BookRepository.getInstance().query(new GetBookImageSpecification(book.getId()));
        try {
            while (resultBook.next()) {
                book.setImage(resultBook.getString("b.book_image"));
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        } finally {

                closeConnection(resultBook.getStatement().getConnection());
                closeStatement(resultBook.getStatement());
        }
        return book.getImage();
    }
}