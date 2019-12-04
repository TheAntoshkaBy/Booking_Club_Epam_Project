package by.epam.booking.command.impl.user.change;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.config.MessageManager;
import by.epam.booking.format.PageFormat;
import by.epam.booking.logic.user.changeLogic.ChangeSurnameLogic;
import by.epam.booking.logic.user.changeLogic.ChangeUsernameLogic;
import by.epam.booking.specification.impl.user.update.UpdateSurnameByLogin;
import by.epam.booking.specification.impl.user.update.UpdateUsernameByLogin;

import javax.servlet.http.HttpServletRequest;

public class ChangeProfileSurname implements WebCommand {

    @Override
    public PageFormat execute(HttpServletRequest request) {
        PageFormat page = new PageFormat();

        if(!request.getParameter("surname").isEmpty()){
            ChangeSurnameLogic.changeSurname(new UpdateSurnameByLogin(
                    (String) request.getSession().getAttribute("login"),
                    request.getParameter("surname")));

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
