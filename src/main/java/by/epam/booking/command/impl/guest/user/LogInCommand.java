package by.epam.booking.command.impl.guest.user;

import by.epam.booking.command.WebCommand;
import by.epam.booking.command.validator.UserValidator;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.config.MessageManager;
import by.epam.booking.enumeration.PageFormatList;
import by.epam.booking.format.PageFormat;
import by.epam.booking.repository.assistant.user.UserInfoByLogin;
import by.epam.booking.repository.assistant.user.Login;
import by.epam.booking.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class LogInCommand implements WebCommand {

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public PageFormat execute(HttpServletRequest request) {
        PageFormat page = new PageFormat();
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        HttpSession session = request.getSession();

            if(UserValidator.isUserConsist(login,password))
            {
                User user = UserInfoByLogin.searchUserByLogin(login);
                request.setAttribute("login", user.getLogin());
                request.setAttribute("user", user.getName());
                request.setAttribute("surname", user.getSurname());
                request.setAttribute("email", user.getEmail());
                request.setAttribute("role", user.getRole().name());
                request.setAttribute("status", user.isActive() );
                request.setAttribute("type","see");

                page.setPageFormat(PageFormatList.FORWARD);
                page.setPage(ConfigurationManager.getProperty("path.page.user"));
                session.setAttribute("login",user.getLogin());
                session.setAttribute("name",user.getName());
                session.setAttribute("surname",user.getSurname());
                session.setAttribute("role",user.getRole());
            }else {
                page.setPage( ConfigurationManager.getProperty("path.page.login"));
                request.getSession().setAttribute("loginError", MessageManager.getProperty("message.loginError"));
                return page;
            }

        return page;
    }

}
