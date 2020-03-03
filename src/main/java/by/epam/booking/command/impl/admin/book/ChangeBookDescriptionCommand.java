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

public class ChangeBookDescriptionCommand implements WebCommand {

    private final String VALUE_FOR_PAGE = "settings";
    private final String PAGE_PATH = "path.page.book";// FIXME: 27.02.2020 Разные названия констант
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws SQLException, RepositoryException {
        Router page;
        Book book;
        book = new Book();
        book.setId(Integer.parseInt(request.getParameter(ParameterName.PARAM_BOOK_ID)));
        book = BookLogic.bookGet(book, BookInfoType.ALL);

        if (!request.getParameter(ParameterName.PARAM_BOOK_DESCRIPTION).isEmpty()) {
            book.setDescription(request.getParameter(ParameterName.PARAM_BOOK_DESCRIPTION));
            BookLogic.bookUpdate(book, book, BookInfoType.DESCRIPTION);
            request.setAttribute(ParameterName.PARAM_BOOK_DESCRIPTION, book.getDescription());
            logger.debug("Book description changed!");
        }else {
            logger.warn("Book description don't changed just because (Empty field)!");
        }

        request.getSession().setAttribute(ParameterName.PARAM_SETTINGS_FOR_BOOK_PAGE, VALUE_FOR_PAGE);
        page = new Router(PageChangeType.FORWARD, ConfigurationManager.getProperty(PAGE_PATH));

        return page;
    }
}
