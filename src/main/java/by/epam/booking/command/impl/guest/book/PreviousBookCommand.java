package by.epam.booking.command.impl.guest.book;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.Book;
import by.epam.booking.command.Router;
import by.epam.booking.exception.RepositoryException;
import by.epam.booking.service.book.BookInfoType;
import by.epam.booking.service.book.BookLogic;
import by.epam.booking.type.PageChangeType;
import by.epam.booking.type.ParameterName;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.SQLException;

public class PreviousBookCommand implements WebCommand {

    public static final String UPLOAD_DIR = "book_image";
    public static final String PATH_PAGE = "path.page.book";

    @Override
    public Router execute(HttpServletRequest request) throws SQLException, RepositoryException {

        Book book = new Book();
        book.setId(Integer.parseInt(request.getParameter(ParameterName.PARAM_BOOK_ID)) - 1);
        if (BookLogic.bookGet(book, BookInfoType.ALL) != null) {
            request.getSession().setAttribute(ParameterName.PARAM_BOOK_ID, book.getId());
            request.getSession().setAttribute(ParameterName.BOOK_NAME_PARAMETER, book.getName());
            request.getSession().setAttribute(ParameterName.PARAM_BOOK_AUTHOR, book.getAuthor());
            request.getSession().setAttribute(ParameterName.PARAM_BOOK_DESCRIPTION, book.getDescription());
            request.getSession().setAttribute(ParameterName.PARAM_BOOK_COUNT, book.getCount());
            request.getSession().setAttribute(ParameterName.PARAM_BOOK_COMMENTS, book.getComments());
            request.getSession().setAttribute(ParameterName.PARAM_BOOK_IMAGE, UPLOAD_DIR + File.separator + book.getImage());

        } else {
            Book endBook = new Book();
            endBook = BookLogic.bookGet(endBook, BookInfoType.GET_MAX_ID);
            book.setId(endBook.getId());
            BookLogic.bookGet(book, BookInfoType.ALL);
            request.setAttribute(ParameterName.PARAM_BOOK_ID, book.getId());
            request.setAttribute(ParameterName.BOOK_NAME_PARAMETER, book.getName());
            request.setAttribute(ParameterName.PARAM_BOOK_AUTHOR, book.getAuthor());
            request.setAttribute(ParameterName.PARAM_BOOK_DESCRIPTION, book.getDescription());
            request.setAttribute(ParameterName.PARAM_BOOK_COUNT, book.getCount());
            request.setAttribute(ParameterName.PARAM_BOOK_COMMENTS, book.getComments());
            request.setAttribute(ParameterName.PARAM_BOOK_IMAGE, UPLOAD_DIR + File.separator + book.getImage());
        }

        Router page = new Router(PageChangeType.FORWARD, ConfigurationManager.getProperty(PATH_PAGE));
        return page;
    }
}
