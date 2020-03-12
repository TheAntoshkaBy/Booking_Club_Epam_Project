package by.epam.booking.command.impl.user;

import by.epam.booking.command.WebCommand;
import by.epam.booking.command.impl.BookingClubCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.Book;
import by.epam.booking.entity.Comment;
import by.epam.booking.command.Router;
import by.epam.booking.exception.CommandException;
import by.epam.booking.exception.ServiceException;
import by.epam.booking.service.book.BookInfoType;
import by.epam.booking.type.PageChangeType;
import by.epam.booking.type.ParameterName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class NewCommentCommand extends BookingClubCommand implements WebCommand {

    private static final String PATH_PAGE = "path.page.book";
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        String commentHeader = request.getParameter(ParameterName.COMMENT_HEADER);
        String text = request.getParameter(ParameterName.COMMENT_BODY_TEXT);
        String login = (String) request.getSession().getAttribute(ParameterName.PARAM_USER_LOGIN);
        Date date = new Date();
        long dateInBd = date.getTime();
        Book book = new Book();
        book.setId(Integer.parseInt(request.getParameter(ParameterName.PARAM_BOOK_ID)));
        book.setBuffDate(dateInBd);
        Comment comment = new Comment(book.getId(), login, text, commentHeader);
        book.setBuffComment(comment);
        bookLogic.bookUpdate(book, book, BookInfoType.ADD_COMMENT);
        try {
            book = bookLogic.bookGet(book, BookInfoType.ALL);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        assert book != null;
        request.getSession().setAttribute(ParameterName.PARAM_BOOK_ID, book.getId());
        request.getSession().setAttribute(ParameterName.BOOK_NAME_PARAMETER, book.getName());
        request.getSession().setAttribute(ParameterName.PARAM_BOOK_AUTHOR, book.getAuthor());
        request.getSession().setAttribute(ParameterName.PARAM_BOOK_DESCRIPTION, book.getDescription());
        request.getSession().setAttribute(ParameterName.PARAM_BOOK_COUNT, book.getCount());
        request.getSession().setAttribute(ParameterName.PARAM_BOOK_COMMENTS, book.getComments());
        Router page = new Router(PageChangeType.REDIRECT, ConfigurationManager.getPath(PATH_PAGE));
        return page;
    }
}
