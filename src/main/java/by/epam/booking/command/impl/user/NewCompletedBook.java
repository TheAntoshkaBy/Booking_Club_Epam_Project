package by.epam.booking.command.impl.user;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.User;
import by.epam.booking.command.Router;
import by.epam.booking.exception.RepositoryException;
import by.epam.booking.service.user.UserInfoType;
import by.epam.booking.service.user.UserLogic;
import by.epam.booking.type.PageChangeType;
import by.epam.booking.type.ParameterName;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class NewCompletedBook implements WebCommand {

    private static final String PATH_PAGE = "path.page.user";
    private static final String PARAM_BOOK_STATUS_VALUE = "completed";
    private static final String PARAM_TYPE_VALUE = "change";

    @Override
    public Router execute(HttpServletRequest request) throws RepositoryException {
        Router page = new Router();

        ArrayList<Integer> booksId = (ArrayList<Integer>) request.getSession()
                .getAttribute(ParameterName.PARAM_LIST_OF_USER_COMPLETED_BOOKS);
        Integer bookId = (Integer) request.getSession().getAttribute(ParameterName.PARAM_USER_BOOK_ID);
        User user = new User();

        user.setLogin((String) request.getSession().getAttribute(ParameterName.PARAM_USER_LOGIN));
        user.setBookId(bookId);
        UserLogic.userUpdate(user, user, UserInfoType.ADD_NEW_BOOK_COMPLETED);

        booksId.add(bookId);
        request.getSession().setAttribute(ParameterName.PARAM_LIST_OF_USER_COMPLETED_BOOKS, booksId);
        request.getSession().setAttribute(ParameterName.PARAM_USER_BOOK_STATUS, PARAM_BOOK_STATUS_VALUE);
        request.getSession().setAttribute(ParameterName.PARAM_TYPE_PROFILE, PARAM_TYPE_VALUE);
        page.setPage(ConfigurationManager.getProperty(PATH_PAGE));
        page.setPageFormat(PageChangeType.REDIRECT);

        return page;
    }
}
