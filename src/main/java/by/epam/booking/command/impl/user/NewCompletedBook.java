package by.epam.booking.command.impl.user;

import by.epam.booking.command.WebCommand;
import by.epam.booking.command.impl.BookingClubCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.User;
import by.epam.booking.command.Router;
import by.epam.booking.exception.CommandException;
import by.epam.booking.exception.ServiceException;
import by.epam.booking.service.user.UserInfoType;
import by.epam.booking.service.user.impl.UserLogic;
import by.epam.booking.type.PageChangeType;
import by.epam.booking.type.ParameterName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class NewCompletedBook extends BookingClubCommand implements WebCommand {

    private static final String PATH_PAGE = "path.page.user";
    private static final String PARAM_BOOK_STATUS_VALUE = "completed";
    private static final String PARAM_TYPE_VALUE = "change";
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router page = new Router();

        ArrayList<Integer> booksId = (ArrayList<Integer>) request.getSession()
                .getAttribute(ParameterName.PARAM_LIST_OF_USER_COMPLETED_BOOKS);
        Integer bookId = (Integer) request.getSession().getAttribute(ParameterName.PARAM_USER_BOOK_ID);
        User user = new User();

        user.setLogin((String) request.getSession().getAttribute(ParameterName.PARAM_USER_LOGIN));
        user.setBookId(bookId);
        try {
            userLogic.userUpdate(user, user, UserInfoType.ADD_NEW_BOOK_COMPLETED);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }

        booksId.add(bookId);
        request.getSession().setAttribute(ParameterName.PARAM_LIST_OF_USER_COMPLETED_BOOKS, booksId);
        request.getSession().setAttribute(ParameterName.PARAM_USER_BOOK_STATUS, PARAM_BOOK_STATUS_VALUE);
        request.getSession().setAttribute(ParameterName.PARAM_TYPE_PROFILE, PARAM_TYPE_VALUE);
        page.setPage(ConfigurationManager.getProperty(PATH_PAGE));
        page.setPageFormat(PageChangeType.REDIRECT);

        return page;
    }
}
