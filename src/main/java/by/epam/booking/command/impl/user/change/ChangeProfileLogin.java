package by.epam.booking.command.impl.user.change;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.config.MessageManager;
import by.epam.booking.entity.User;
import by.epam.booking.format.PageFormat;
import by.epam.booking.logic.Logic;
import by.epam.booking.logic.user.LoginLogic;
import by.epam.booking.logic.user.changeLogic.ChangeUsernameLogic;
import by.epam.booking.specification.impl.user.update.UpdateLoginByLogin;
import by.epam.booking.specification.impl.user.update.UpdateUsernameByLogin;

import javax.servlet.http.HttpServletRequest;

public class ChangeProfileLogin implements WebCommand {
    @Override
    public PageFormat execute(HttpServletRequest request) {
        PageFormat page = new PageFormat();

        if(!request.getParameter("login").isEmpty()){
            if(!LoginLogic.isLoginExist(request.getParameter("login"))){
                LoginLogic.changeLogIn(new UpdateLoginByLogin(
                        (String) request.getSession().getAttribute("login"),
                        request.getParameter("login")));

            }else {
                page.setPage(ConfigurationManager.getProperty("path.page.user"));
                request.setAttribute("type","change");
                request.getSession().setAttribute("usernameError",
                        MessageManager.getProperty("message.login.consist"));
                return page;
            }
        }else {
            page.setPage(ConfigurationManager.getProperty("path.page.user"));
            request.setAttribute("type","change");
            request.getSession().setAttribute("usernameError", MessageManager.getProperty("message.loginEmpty"));
            return page;
        }
        request.getSession().setAttribute("login",request.getParameter("login"));
        page.setPage(ConfigurationManager.getProperty("path.page.user"));
        request.setAttribute("type","change");
        request.getSession().setAttribute("usernameError","");
        request.getSession().setAttribute("ChangedSave",MessageManager.getProperty("message.changed.Save"));
        return page;

    }
}
