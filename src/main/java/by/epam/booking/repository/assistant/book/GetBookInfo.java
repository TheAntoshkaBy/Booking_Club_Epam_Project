package by.epam.booking.repository.assistant.book;

import by.epam.booking.entity.Book;
import by.epam.booking.exception.RepositoryException;
import by.epam.booking.repository.assistant.RepositoryHelper;
import by.epam.booking.repository.impl.BookRepository;
import by.epam.booking.specification.impl.book.GetBookImageSpecification;
import by.epam.booking.specification.impl.book.SelectBookByIdSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetBookInfo extends RepositoryHelper {

    private static Logger logger = LogManager.getLogger();

    public static boolean getBookById(Book book) throws RepositoryException{
        ResultSet resultBook = BookRepository.getInstance().query(new SelectBookByIdSpecification(book.getId()));

        try {
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
            } finally {
                closeConnection(resultBook.getStatement().getConnection());
                closeStatement(resultBook.getStatement());
            }
        }catch (SQLException e){
            logger.error(e);
            throw new RepositoryException(e);
        }

        return true;
    }

    public static String getBookImageById(Book book) throws RepositoryException {
        ResultSet resultBook = BookRepository.getInstance().query(new GetBookImageSpecification(book.getId()));

        try {
            try {
                while (resultBook.next()) {
                    book.setImage(resultBook.getString("b.book_image"));
                }
            } finally {
                closeConnection(resultBook.getStatement().getConnection());
                closeStatement(resultBook.getStatement());
            }
        }catch (SQLException e){
            logger.error(e);
            throw new RepositoryException(e);
        }

        return book.getImage();
    }
}