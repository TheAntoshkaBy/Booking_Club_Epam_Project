package by.epam.booking.command.impl.user;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.User;
import by.epam.booking.command.Router;
import by.epam.booking.exception.RepositoryException;
import by.epam.booking.service.user.UserInfoType;
import by.epam.booking.service.user.UserLogic;
import by.epam.booking.type.ParameterName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SettingsProfileCommand implements WebCommand {

    private static final String PATH_PAGE_REGISTRATION = "path.page.registration";
    private static final String PATH_PAGE_USER = "path.page.user";
    private static final String PARAM_CHANGE_VALUE = "change";

    @Override
    public Router execute(HttpServletRequest request) throws RepositoryException {
        Router page = new Router();
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute(ParameterName.PARAM_USER_LOGIN);
        if (login == null) {
            page.setPage(ConfigurationManager.getProperty(PATH_PAGE_REGISTRATION));
        } else {
            User searchedUser = new User();
            searchedUser.setLogin(login);
            User user = UserLogic.userGet(searchedUser, UserInfoType.ALL);
            request.getSession().setAttribute(ParameterName.PARAM_USER_LOGIN, user.getLogin());
            request.getSession().setAttribute(ParameterName.PARAM_USER_INTERIM, user.getName());
            request.getSession().setAttribute(ParameterName.PARAM_USER_SURNAME, user.getSurname());
            request.getSession().setAttribute(ParameterName.PARAM_USER_EMAIL, user.getEmail());
            request.getSession().setAttribute(ParameterName.PARAM_USER_ROLE, user.getRole().name());
            request.getSession().setAttribute(ParameterName.PARAM_USER_STATUS, user.isActive());

            request.setAttribute(ParameterName.PARAM_TYPE_PROFILE, PARAM_CHANGE_VALUE);
            request.getSession().setAttribute(ParameterName.PARAM_LOGIN_ERROR, "");
            session.setAttribute(ParameterName.PARAM_USER_LOGIN, user.getLogin());
            page.setPage(ConfigurationManager.getProperty(PATH_PAGE_USER));
        }

        request.getSession().setAttribute(ParameterName.PARAM_CHANGE_SAVED, "");

        return page;
    }
}
