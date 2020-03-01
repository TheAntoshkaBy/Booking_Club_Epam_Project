package by.epam.booking.command.impl.user;

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
import java.util.ArrayList;

public class ToListCompleteBooks implements WebCommand {

    private static final String PATH_PAGE = "path.page.completed.books";
    private static final String PARAM_TYPE_VALUE = "see";

    @Override
    public Router execute(HttpServletRequest request) throws SQLException, RepositoryException {
        Router page = new Router();
        ArrayList<Integer> idBooks = (ArrayList<Integer>) request.getSession()
                .getAttribute(ParameterName.PARAM_LIST_OF_USER_COMPLETED_BOOKS);
        ArrayList<Book> books = new ArrayList<Book>();
        for (int i = 0; i < idBooks.size(); i++) {
            Book book = new Book();
            book.setId(idBooks.get(i));
            books.add(BookLogic.bookGet(book, BookInfoType.ALL));
        }
        request.setAttribute(ParameterName.PARAM_COMPLETED_BOOKS, books);
        request.getSession().setAttribute(ParameterName.PARAM_TYPE_PROFILE, PARAM_TYPE_VALUE);
        page.setPage(ConfigurationManager.getProperty(PATH_PAGE));
        page.setPageFormat(PageChangeType.FORWARD);
        return page;
    }
}
