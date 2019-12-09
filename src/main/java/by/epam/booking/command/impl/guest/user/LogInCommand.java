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
import by.epam.booking.service.user.UserInfoType;
import by.epam.booking.service.user.UserLogic;

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
                User user = new User();
                user.setLogin(login);
                user = UserLogic.userGet(user, UserInfoType.ALL, UserInfoType.BOOK_NAME);
                request.getSession().setAttribute("login", user.getLogin());
                request.getSession().setAttribute("user", user.getName());
                request.getSession().setAttribute("surname", user.getSurname());
                request.getSession().setAttribute("email", user.getEmail());
                request.getSession().setAttribute("role", user.getRole().name());
                request.getSession().setAttribute("status", user.isActive() );
                request.getSession().setAttribute("book", user.getBookName());
                request.getSession().setAttribute("money", user.getMoneyBalance());
                request.getSession().setAttribute("type","see");

                page.setPageFormat(PageFormatList.FORWARD);
                page.setPage(ConfigurationManager.getProperty("path.page.user"));

            }else {
                page.setPage( ConfigurationManager.getProperty("path.page.login"));
                request.getSession().setAttribute("loginError", MessageManager.getProperty("message.loginError"));
                return page;
            }

        return page;
    }

}
