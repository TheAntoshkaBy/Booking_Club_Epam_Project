package by.epam.booking.command.impl.user.change;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.config.MessageManager;
import by.epam.booking.entity.User;
import by.epam.booking.format.PageFormat;
import by.epam.booking.service.user.UserInfoType;
import by.epam.booking.service.user.UserLogic;

import javax.servlet.http.HttpServletRequest;

public class ChangeProfileName implements WebCommand {
    @Override
    public PageFormat execute(HttpServletRequest request) {
        PageFormat page = new PageFormat();
        User user = new User();

        if(!request.getParameter("name").isEmpty()){
            user.setLogin((String) request.getSession().getAttribute("login"));
            user.setName(request.getParameter("name"));
            UserLogic.userUpdate(user,user, UserInfoType.NAME);

        }else {
            page.setPage(ConfigurationManager.getProperty("path.page.user"));
            request.setAttribute("type","change");
            request.getSession().setAttribute("usernameError", MessageManager.getProperty("message.usernameEmpty"));
            return page;
        }
        page.setPage(ConfigurationManager.getProperty("path.page.user"));
        request.setAttribute("type","change");
        request.getSession().setAttribute("name",user.getName());
        request.getSession().setAttribute("usernameError","");
        request.getSession().setAttribute("ChangedSave",MessageManager.getProperty("message.changed.Save"));
        return page;
    }
}
