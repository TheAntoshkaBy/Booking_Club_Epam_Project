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

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class ToBookSettingsCommand implements WebCommand {

    private static final String SETTINGS_PARAM_VALUE = "settings";
    private static final String PATH_PAGE = "path.page.book";
    private Book book;

    @Override
    public Router execute(HttpServletRequest request) throws SQLException, RepositoryException {
        Router page;

        book = new Book();
        book.setId(Integer.parseInt(request.getParameter(ParameterName.PARAM_BOOK_ID)));
        book = BookLogic.bookGet(book, BookInfoType.ALL);

        request.setAttribute(ParameterName.PARAM_BOOK_ID, book.getId());
        request.setAttribute(ParameterName.BOOK_NAME_PARAMETER, book.getName());
        request.setAttribute(ParameterName.PARAM_BOOK_AUTHOR, book.getAuthor());
        request.setAttribute(ParameterName.PARAM_BOOK_DESCRIPTION, book.getDescription());
        request.setAttribute(ParameterName.PARAM_BOOK_COUNT, book.getCount());
        request.setAttribute(ParameterName.PARAM_BOOK_COMMENTS, book.getComments());
        request.getSession().setAttribute(ParameterName.PARAM_SETTINGS_FOR_BOOK_PAGE, SETTINGS_PARAM_VALUE);
        page = new Router(PageChangeType.FORWARD, ConfigurationManager.getProperty(PATH_PAGE));
        return page;
    }
}
