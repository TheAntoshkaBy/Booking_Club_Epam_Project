package by.epam.booking.command.impl.user;

import by.epam.booking.command.WebCommand;
import by.epam.booking.command.impl.BookingClubCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.Book;
import by.epam.booking.command.Router;
import by.epam.booking.exception.CommandException;
import by.epam.booking.exception.ServiceException;
import by.epam.booking.service.book.BookInfoType;
import by.epam.booking.type.PageChangeType;
import by.epam.booking.type.ParameterName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class ToListCompleteBooks extends BookingClubCommand implements WebCommand {

    private static final String PATH_PAGE = "path.page.completed.books";
    private static final String PARAM_TYPE_VALUE = "see";
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router page = new Router();
        ArrayList<Integer> idBooks = (ArrayList<Integer>) request.getSession()
                .getAttribute(ParameterName.PARAM_LIST_OF_USER_COMPLETED_BOOKS);
        ArrayList<Book> books = new ArrayList<Book>();
        for (int i = 0; i < idBooks.size(); i++) {
            Book book = new Book();
            book.setId(idBooks.get(i));
            try {
                books.add( bookLogic.bookGet(book, BookInfoType.ALL));
            } catch (ServiceException e) {
                logger.error(e);
                throw new CommandException(e);
            }
        }
        request.setAttribute(ParameterName.PARAM_COMPLETED_BOOKS, books);
        request.getSession().setAttribute(ParameterName.PARAM_TYPE_PROFILE, PARAM_TYPE_VALUE);
        page.setPage(ConfigurationManager.getProperty(PATH_PAGE));
        page.setPageFormat(PageChangeType.FORWARD);
        return page;
    }
}
