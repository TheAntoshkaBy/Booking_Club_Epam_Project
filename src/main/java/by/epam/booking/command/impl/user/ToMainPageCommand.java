package by.epam.booking.command.impl.user;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.command.Router;
import by.epam.booking.type.ParameterName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class ToMainPageCommand implements WebCommand {

    private static final String PATH_PAGE_REGISTRATION = "path.page.registration";
    private static final String PATH_PAGE_USER = "path.page.user";
    private static final String PARAM_BOOK_STATUS_VALUE = "completed";
    private static final String PARAM_TYPE_PROFILE_PAGE_VALUE = "see";

    @Override
    public Router execute(HttpServletRequest request) {
        Router page = new Router();
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute(ParameterName.PARAM_USER_LOGIN);
        if (login == null) {
            page.setPage(ConfigurationManager.getProperty(PATH_PAGE_REGISTRATION));
        } else {
            ArrayList<Integer> booksId = (ArrayList<Integer>) request.getSession()
                    .getAttribute(ParameterName.PARAM_LIST_OF_USER_COMPLETED_BOOKS);
            Integer bookId = (Integer) request.getSession().getAttribute(ParameterName.PARAM_USER_BOOK_ID);
            if (booksId.contains(bookId)) {
                request.getSession().setAttribute(ParameterName.PARAM_USER_BOOK_STATUS, PARAM_BOOK_STATUS_VALUE);
            } else {
                request.getSession().setAttribute(ParameterName.PARAM_USER_BOOK_STATUS, null);
            }
            request.getSession().setAttribute(ParameterName.PARAM_TYPE_PROFILE, PARAM_TYPE_PROFILE_PAGE_VALUE);
            page.setPage(ConfigurationManager.getProperty(PATH_PAGE_USER));
        }
        return page;
    }
}
