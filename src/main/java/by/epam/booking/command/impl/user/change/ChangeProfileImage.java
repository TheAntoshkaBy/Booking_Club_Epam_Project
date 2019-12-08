package by.epam.booking.command.impl.user.change;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.config.MessageManager;
import by.epam.booking.entity.User;
import by.epam.booking.format.PageFormat;

import javax.servlet.ServletException;
import javax.servlet.http.Part;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ChangeProfileImage implements WebCommand{
    @Override
    public PageFormat execute(HttpServletRequest request) {
        PageFormat page = new PageFormat();
        User user = new User();

        if(!request.getParameter("image").isEmpty()){
            try {
               Part part = request.getParts().stream().findAny().get();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }else {
            page.setPage(ConfigurationManager.getProperty("path.page.user"));
            request.setAttribute("type","change");
            return page;
        }
        page.setPage(ConfigurationManager.getProperty("path.page.user"));
        request.setAttribute("type","change");
        request.getSession().setAttribute("usernameError","");
        request.getSession().setAttribute("ChangedSave",MessageManager.getProperty("message.changed.Save"));
        return page;
    }
}
