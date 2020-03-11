package by.epam.booking.command.impl.guest.book;

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
import java.io.File;
import java.util.ArrayList;

public class PreviousBookCommand extends BookingClubCommand implements WebCommand {

    public static final String UPLOAD_DIR = "book_image";
    public static final String PATH_PAGE = "path.page.book";
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException{

        ArrayList<Integer> booksId = (ArrayList<Integer>) request.getSession().getAttribute(ParameterName.PARAM_ALL_BOOKS_ID);

        Book book = new Book();
        book.setId(Integer.parseInt(request.getParameter(ParameterName.PARAM_BOOK_ID)));
        if(booksId.indexOf(book.getId()) == 0)
        {
            book.setId(booksId.size());
        }else {
            book.setId(booksId.get(booksId.indexOf(book.getId()) - 1));
        }
        logger.debug("Next book id " + book.getId());
        try {
            bookLogic.bookGet(book, BookInfoType.ALL) ;
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        request.getSession().setAttribute(ParameterName.PARAM_BOOK_ID, book.getId());
            request.getSession().setAttribute(ParameterName.BOOK_NAME_PARAMETER, book.getName());
            request.getSession().setAttribute(ParameterName.PARAM_BOOK_AUTHOR, book.getAuthor());
            request.getSession().setAttribute(ParameterName.PARAM_BOOK_DESCRIPTION, book.getDescription());
            request.getSession().setAttribute(ParameterName.PARAM_BOOK_COUNT, book.getCount());
            request.getSession().setAttribute(ParameterName.PARAM_BOOK_COMMENTS, book.getComments());
            request.getSession().setAttribute(ParameterName.PARAM_BOOK_IMAGE, UPLOAD_DIR + File.separator + book.getImage());


        Router page = new Router(PageChangeType.FORWARD, ConfigurationManager.getProperty(PATH_PAGE));
        return page;
    }
}
