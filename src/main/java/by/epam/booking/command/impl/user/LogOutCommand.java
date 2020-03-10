package by.epam.booking.command.impl.user;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.command.Router;
import by.epam.booking.type.PageChangeType;
import by.epam.booking.type.ParameterName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogOutCommand implements WebCommand {

    private static final String PATH_PAGE = "path.page.login";

    @Override
    public Router execute(HttpServletRequest request) {
        Router page = new Router();
        page.setPage(ConfigurationManager.getProperty(PATH_PAGE));
        HttpSession session = request.getSession();
        session.setAttribute(ParameterName.PARAM_USER_LOGIN, null);
        request.getSession().setAttribute(ParameterName.PARAM_USER_INTERIM, null);
        request.getSession().setAttribute(ParameterName.PARAM_USER_SURNAME, null);
        request.getSession().setAttribute(ParameterName.PARAM_USER_EMAIL, null);
        request.getSession().setAttribute(ParameterName.PARAM_USER_ROLE, null);
        request.getSession().setAttribute(ParameterName.PARAM_USER_STATUS, null);
        request.getSession().setAttribute(ParameterName.PARAM_USER_BOOK_NAME, null);
        request.getSession().setAttribute(ParameterName.PARAM_USER_BOOK_ID, null);
        request.getSession().setAttribute(ParameterName.PARAM_NAME_OF_READING_PLAN, null);
        request.getSession().setAttribute(ParameterName.PARAM_USER_PLAN_ID, null);
        request.getSession().setAttribute(ParameterName.PARAM_LIST_OF_USER_COMPLETED_BOOKS, null);
        request.getSession().setAttribute(ParameterName.PARAM_COMPLETED_BOOKS, null);

        page.setPageFormat(PageChangeType.REDIRECT);
        return page;
    }
}
