package by.epam.booking.command.impl.user.change;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.config.MessageManager;
import by.epam.booking.entity.User;
import by.epam.booking.format.PageFormat;
import by.epam.booking.repository.assistant.user.changeLogic.ChangeSurname;
import by.epam.booking.service.UserInfoType;
import by.epam.booking.service.UserLogic;
import by.epam.booking.specification.impl.user.update.UpdateSurnameByLogin;

import javax.servlet.http.HttpServletRequest;

public class ChangeProfileSurname implements WebCommand {

    @Override
    public PageFormat execute(HttpServletRequest request) {
        PageFormat page = new PageFormat();

        if(!request.getParameter("surname").isEmpty()){
            User transferredUser = new User();
            transferredUser.setLogin((String) request.getSession().getAttribute("login"));
            transferredUser.setSurname(request.getParameter("surname"));
            UserLogic.userUpdate(transferredUser,transferredUser, UserInfoType.SURNAME);
        }else {
            page.setPage(ConfigurationManager.getProperty("path.page.user"));
            request.setAttribute("type","change");
            request.getSession().setAttribute("surnameError", MessageManager.getProperty("message.surnameEmpty"));
            return page;
        }
        page.setPage(ConfigurationManager.getProperty("path.page.user"));
        request.setAttribute("type","change");
        request.getSession().setAttribute("surnameError","");
        request.getSession().setAttribute("ChangedSave",MessageManager.getProperty("message.changed.Save"));
        return page;
    }
}
