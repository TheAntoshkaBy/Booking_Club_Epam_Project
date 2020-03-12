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

public class ChangeBookAuthorClubCommand extends BookingClubCommand implements WebCommand {

    private final String PATH_TO_CHANGE_BOOK_SETTINGS = "path.page.book";
    private final String VALUE_FOR_BOOK_PAGE_SETTINGS = "settings";
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

        if (!request.getParameter(ParameterName.PARAM_BOOK_AUTHOR).isEmpty()) {
            book.setAuthor(request.getParameter(ParameterName.PARAM_BOOK_AUTHOR));
            bookLogic.bookUpdate(book, book, BookInfoType.AUTHOR);
            request.getSession().setAttribute(ParameterName.PARAM_BOOK_AUTHOR, book.getAuthor());
            logger.debug("Author of book " + book + " successfully added!");
        }else {
            logger.warn("Author don't changed because gladiolus");
        }

        request.getSession().setAttribute(ParameterName.PARAM_SETTINGS_FOR_BOOK_PAGE, VALUE_FOR_BOOK_PAGE_SETTINGS);
        page = new Router(PageChangeType.FORWARD, ConfigurationManager.getPath(PATH_TO_CHANGE_BOOK_SETTINGS));

        return page;
    }
}
