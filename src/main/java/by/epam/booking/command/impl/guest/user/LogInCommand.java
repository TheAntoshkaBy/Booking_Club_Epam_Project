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
                if(UserValidator.userIsActive(user)){
                    request.getSession().setAttribute("login", user.getLogin());
                    request.getSession().setAttribute("userName", user.getName());
                    request.getSession().setAttribute("surname", user.getSurname());
                    request.getSession().setAttribute("email", user.getEmail());
                    request.getSession().setAttribute("role", user.getRole().name());
                    request.getSession().setAttribute("status", user.isActive());
                    request.getSession().setAttribute("completedBooks", user.getCompletedBooks());
                    if(user.getBookName() != null){
                        request.getSession().setAttribute("bookName", user.getBookName());
                    }else {
                        request.getSession().setAttribute("bookName", "--");
                    }
                    request.getSession().setAttribute("money", user.getMoneyBalance());
                    if(user.getReadingPlanName() != null){
                        request.getSession().setAttribute("readingPlanName",user.getReadingPlanName());
                    }else {
                        request.getSession().setAttribute("readingPlanName","--");
                    }
                    request.getSession().setAttribute("type","see");
                    if(user.getBookId() != 0){
                        request.getSession().setAttribute("userBookId", user.getBookId());
                    }else {
                        request.getSession().setAttribute("userBookId", null);
                    }
                    if(user.getReadingPlanId() != 0){
                        request.getSession().setAttribute("userPlanId", user.getReadingPlanId());
                    }else {
                        request.getSession().setAttribute("userPlanId", null);
                    }


                    page.setPageFormat(PageFormatList.FORWARD);
                    page.setPage(ConfigurationManager.getProperty("path.page.user"));

                }else {
                    page.setPageFormat(PageFormatList.REDIRECT);
                    page.setPage(ConfigurationManager.getProperty("path.page.user.passive"));
                }

            }else {
                page.setPage( ConfigurationManager.getProperty("path.page.login"));
                page.setPageFormat(PageFormatList.REDIRECT);
                request.getSession().setAttribute("loginError", MessageManager.getProperty("message.loginError"));
                return page;
            }

        return page;
    }

}
