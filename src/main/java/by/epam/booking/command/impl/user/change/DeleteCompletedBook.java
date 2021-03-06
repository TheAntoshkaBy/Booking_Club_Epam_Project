package by.epam.booking.command.impl.user.change;

import by.epam.booking.command.WebCommand;
import by.epam.booking.command.impl.BookingClubCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.Book;
import by.epam.booking.entity.User;
import by.epam.booking.exception.CommandException;
import by.epam.booking.exception.ServiceException;
import by.epam.booking.type.PageChangeType;
import by.epam.booking.command.Router;
import by.epam.booking.service.book.BookInfoType;
import by.epam.booking.service.user.UserInfoType;
import by.epam.booking.type.ParameterName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class DeleteCompletedBook extends BookingClubCommand implements WebCommand {

    private static final String PARAM_VALUE_TO_PAGE = "see";
    private static final String PATH_PAGE = "path.page.completed.books";
    private static Logger logger = LogManager.getLogger();


    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router page = new Router();

        ArrayList<Integer> booksId = (ArrayList<Integer>) request.getSession()
                .getAttribute(ParameterName.PARAM_LIST_OF_USER_COMPLETED_BOOKS);
        Integer bookId = Integer.parseInt(request.getParameter(ParameterName.PARAM_BOOK_ID));
        User user = new User();

        user.setLogin((String) request.getSession().getAttribute(ParameterName.PARAM_USER_LOGIN));
        user.setBookId(bookId);
        try {
            userLogic.userUpdate(user,user, UserInfoType.DELETE_BOOK_COMPLETED);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }

        booksId.remove(bookId);
        request.getSession().setAttribute(ParameterName.PARAM_LIST_OF_USER_COMPLETED_BOOKS, booksId);
        ArrayList<Book> books = new ArrayList<Book>();
        for(int i = 0; i < booksId.size();i++){
            Book book = new Book();
            book.setId(booksId.get(i));
            try {
                books.add(bookLogic.bookGet(book, BookInfoType.ALL));
            } catch (ServiceException e) {
                logger.error(e);
                throw new CommandException(e);
            }
        }
        logger.debug("Books " + books +" successfully delete");
        request.getSession().setAttribute(ParameterName.PARAM_COMPLETED_BOOKS, books);
        request.getSession().setAttribute(ParameterName.PARAM_TYPE_PROFILE,PARAM_VALUE_TO_PAGE);
        page.setPage( ConfigurationManager.getPath(PATH_PAGE));
        page.setPageFormat(PageChangeType.REDIRECT);
        return page;
    }
}
