package by.epam.booking.command.impl.user.change;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.config.MessageManager;
import by.epam.booking.entity.User;
import by.epam.booking.format.PageFormat;
import by.epam.booking.service.user.UserInfoType;
import by.epam.booking.service.user.UserLogic;

import javax.servlet.http.HttpServletRequest;

public class ChangeProfileLogin implements WebCommand {
    @Override
    public PageFormat execute(HttpServletRequest request) {
        PageFormat page = new PageFormat();

        if (!request.getParameter("login").isEmpty()) {
            User mutableUser = new User(), changeParamOfUser = new User();
            mutableUser.setLogin((String) request.getSession().getAttribute("login"));
            changeParamOfUser.setLogin(request.getParameter("login"));
            if (UserLogic.userUpdate(mutableUser,changeParamOfUser,UserInfoType.LOGIN)) {
                request.getSession().setAttribute("login", request.getParameter("login"));
                page.setPage(ConfigurationManager.getProperty("path.page.user"));
                request.setAttribute("type", "change");
                request.getSession().setAttribute("login",changeParamOfUser.getLogin());
                request.getSession().setAttribute("usernameError", "");
                request.getSession().setAttribute("ChangedSave", MessageManager.getProperty("message.changed.Save"));
            } else {
                page.setPage(ConfigurationManager.getProperty("path.page.user"));
                request.setAttribute("type", "change");
                request.getSession().setAttribute("usernameError",
                        MessageManager.getProperty("message.login.consist"));
                return page;
            }
        } else {
            page.setPage(ConfigurationManager.getProperty("path.page.user"));
            request.setAttribute("type", "change");
            request.getSession().setAttribute("usernameError", MessageManager.getProperty("message.loginEmpty"));
            return page;
        }

        return page;

    }
}
