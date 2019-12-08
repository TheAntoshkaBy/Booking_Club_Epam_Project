package by.epam.booking.command.impl.user;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.format.PageFormat;
import by.epam.booking.entity.User;
import by.epam.booking.service.user.UserInfoType;
import by.epam.booking.service.user.UserLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ToMainPageCommand implements WebCommand {
    @Override
    public PageFormat execute(HttpServletRequest request) {
        PageFormat page = new PageFormat();
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");
        if(login == null)
        {
            page.setPage(ConfigurationManager.getProperty("path.page.registration"));
        }else{
            User transferredUser = new User();
            transferredUser.setLogin(login);
            User user = UserLogic.userGet(transferredUser, UserInfoType.ALL);
            request.setAttribute("login", user.getLogin());
            request.setAttribute("user", user.getName());
            request.setAttribute("surname", user.getSurname());
            request.setAttribute("email", user.getEmail());
            request.setAttribute("role", user.getRole().name());
            request.setAttribute("status", user.isActive());
            request.setAttribute("type","see");
            page.setPage( ConfigurationManager.getProperty("path.page.user"));
        }
        return page;
    }
}
