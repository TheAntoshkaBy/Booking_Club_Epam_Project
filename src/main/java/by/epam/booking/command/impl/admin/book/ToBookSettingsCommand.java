package by.epam.booking.command.impl.admin.book;

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

public class ToBookSettingsCommand extends BookingClubCommand implements WebCommand {

    private static final String SETTINGS_PARAM_VALUE = "settings";
    private static final String PATH_PAGE = "path.page.book";
    private static Logger logger = LogManager.getLogger();
    private Book book;

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router page;

        book = new Book();
        book.setId(Integer.parseInt(request.getParameter(ParameterName.PARAM_BOOK_ID)));
        try {
            book = bookLogic.bookGet(book, BookInfoType.ALL);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }

        request.getSession().setAttribute(ParameterName.PARAM_SETTINGS_FOR_BOOK_PAGE, SETTINGS_PARAM_VALUE);
        page = new Router(PageChangeType.FORWARD, ConfigurationManager.getPath(PATH_PAGE));
        return page;
    }
}
