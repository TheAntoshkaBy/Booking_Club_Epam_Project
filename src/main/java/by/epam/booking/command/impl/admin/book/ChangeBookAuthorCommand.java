package by.epam.booking.command.impl.admin.book;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.Book;
import by.epam.booking.command.Router;
import by.epam.booking.exception.RepositoryException;
import by.epam.booking.service.book.BookInfoType;
import by.epam.booking.service.book.BookLogic;
import by.epam.booking.type.PageChangeType;
import by.epam.booking.type.ParameterName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class ChangeBookAuthorCommand implements WebCommand {

    private final String PATH = "path.page.book";
    private final String VALUE_FOR_PAGE = "settings";
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws SQLException, RepositoryException {
        Router page;
        Book book;
        book = new Book();// FIXME: 27.02.2020 
        book.setId(Integer.parseInt(request.getParameter(ParameterName.PARAM_BOOK_ID)));
        book = BookLogic.bookGet(book, BookInfoType.ALL);

        request.getSession().setAttribute(ParameterName.PARAM_BOOK_ID, book.getId());
        request.getSession().setAttribute(ParameterName.BOOK_NAME_PARAMETER, book.getName());
        request.getSession().setAttribute(ParameterName.PARAM_BOOK_DESCRIPTION, book.getDescription());
        request.getSession().setAttribute(ParameterName.PARAM_BOOK_COUNT, book.getCount());
        request.getSession().setAttribute(ParameterName.PARAM_BOOK_COMMENT, book.getComments());

        if (!request.getParameter(ParameterName.PARAM_BOOK_AUTHOR).isEmpty()) {
            book.setAuthor(request.getParameter(ParameterName.PARAM_BOOK_AUTHOR));
            BookLogic.bookUpdate(book, book, BookInfoType.AUTHOR);
            request.getSession().setAttribute(ParameterName.PARAM_BOOK_AUTHOR, book.getAuthor());
            logger.debug("Author of book " + book + " successfully added!");
        }

        request.getSession().setAttribute(ParameterName.PARAM_SETTINGS_FOR_BOOK_PAGE, VALUE_FOR_PAGE);
        page = new Router(PageChangeType.FORWARD, ConfigurationManager.getProperty(PATH));

        return page;
    }
}
