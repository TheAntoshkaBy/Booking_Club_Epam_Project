package by.epam.booking.command.impl.guest.user;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.User;
import by.epam.booking.enumeration.Role;
import by.epam.booking.format.PageFormat;
import by.epam.booking.repository.assistant.user.Registration;
import by.epam.booking.service.user.UserLogic;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements WebCommand {

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_EMAIL = "email";
    private static final String NAME_OF_USER = "name";
    private static final String SURNAME_OF_USER = "surname";

    @Override
    public PageFormat execute(HttpServletRequest request) {

        PageFormat page = new PageFormat();
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        String email = request.getParameter(PARAM_NAME_EMAIL);
        String name = request.getParameter(NAME_OF_USER);
        String surname = request.getParameter(SURNAME_OF_USER);
        User user = new User(login,password,email,name,surname, Role.USER,false);
        if(UserLogic.registration(user))
        {
            request.setAttribute("user", name);
            page.setPage(ConfigurationManager.getProperty("path.page.login"));
        }else {
            page.setPage(ConfigurationManager.getProperty("path.page.registration"));
            request.getSession().setAttribute("registrationError", Registration.getReturnedPage());
        }

        return page;
    }
}
