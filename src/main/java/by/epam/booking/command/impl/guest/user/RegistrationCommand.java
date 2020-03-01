package by.epam.booking.command.impl.guest.user;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.User;
import by.epam.booking.command.Router;
import by.epam.booking.exception.RepositoryException;
import by.epam.booking.repository.assistant.user.Registration;
import by.epam.booking.service.user.UserLogic;
import by.epam.booking.type.PageChangeType;
import by.epam.booking.type.ParameterName;
import by.epam.booking.type.UserRoleType;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class RegistrationCommand implements WebCommand {

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_EMAIL = "email";
    private static final String NAME_OF_USER = "name";
    private static final String SURNAME_OF_USER = "surname";
    private static final String PATH_PAGE_TO_LOGIN = "path.page.login";
    private static final String PATH_PAGE_TO_REGISTRATION = "path.page.registration";

    @Override
    public Router execute(HttpServletRequest request) throws SQLException, RepositoryException {

        Router page = new Router();
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        String email = request.getParameter(PARAM_NAME_EMAIL);
        String name = request.getParameter(NAME_OF_USER);
        String surname = request.getParameter(SURNAME_OF_USER);
        User user = new User(login, password, email, name, surname, UserRoleType.USER, false);
        if (UserLogic.registration(user)) {
            request.setAttribute(ParameterName.PARAM_USER_INTERIM, name);
            page.setPageFormat(PageChangeType.REDIRECT);
            page.setPage(ConfigurationManager.getProperty(PATH_PAGE_TO_LOGIN));
        } else {
            page.setPage(ConfigurationManager.getProperty(PATH_PAGE_TO_REGISTRATION));
            request.getSession().setAttribute(ParameterName.PARAM_REGISTRATION_ERROR, Registration.getReturnedPage());
        }

        return page;
    }
}
