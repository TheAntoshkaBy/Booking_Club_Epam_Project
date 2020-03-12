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
        page.setPage(ConfigurationManager.getPath(PATH_PAGE));
        HttpSession session = request.getSession();
        session.removeAttribute(ParameterName.PARAM_USER_LOGIN);
        request.getSession().removeAttribute(ParameterName.PARAM_USER_INTERIM);
        request.getSession().removeAttribute(ParameterName.PARAM_USER_SURNAME);
        request.getSession().removeAttribute(ParameterName.PARAM_USER_EMAIL);
        request.getSession().removeAttribute(ParameterName.PARAM_USER_ROLE);
        request.getSession().removeAttribute(ParameterName.PARAM_USER_STATUS);
        request.getSession().removeAttribute(ParameterName.PARAM_USER_BOOK_NAME);
        request.getSession().removeAttribute(ParameterName.PARAM_USER_BOOK_ID);
        request.getSession().removeAttribute(ParameterName.PARAM_NAME_OF_READING_PLAN);
        request.getSession().removeAttribute(ParameterName.PARAM_USER_PLAN_ID);
        request.getSession().removeAttribute(ParameterName.PARAM_LIST_OF_USER_COMPLETED_BOOKS);
        request.getSession().removeAttribute(ParameterName.PARAM_COMPLETED_BOOKS);

        page.setPageFormat(PageChangeType.REDIRECT);
        return page;
    }
}
