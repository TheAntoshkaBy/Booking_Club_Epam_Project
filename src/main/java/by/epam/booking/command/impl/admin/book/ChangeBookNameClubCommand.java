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

public class ChangeBookNameClubCommand extends BookingClubCommand implements WebCommand {

    private final String VALUE_FOR_PAGE = "settings";
    private final String PAGE_PATH = "path.page.book";
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router page;
        Book book;
        book = new Book();
        book.setId(Integer.parseInt(request.getParameter(ParameterName.PARAM_BOOK_ID)));
        try {
            book = bookLogic.bookGet(book, BookInfoType.ALL);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        assert book != null;
        request.setAttribute(ParameterName.PARAM_BOOK_ID, book.getId());

        if (!request.getParameter(ParameterName.BOOK_NAME_PARAMETER).isEmpty()) {
            book.setName(request.getParameter(ParameterName.BOOK_NAME_PARAMETER));
            bookLogic.bookUpdate(book, book, BookInfoType.NAME);
            request.setAttribute(ParameterName.BOOK_NAME_PARAMETER, book.getName());
            logger.debug("Book name successfully changed!" +  book);
        }else {
            logger.warn("Empty book name field!");
        }

        request.getSession().setAttribute(ParameterName.PARAM_SETTINGS_FOR_BOOK_PAGE, VALUE_FOR_PAGE);
        page = new Router(PageChangeType.FORWARD, ConfigurationManager.getPath(PAGE_PATH));

        return page;
    }
}
