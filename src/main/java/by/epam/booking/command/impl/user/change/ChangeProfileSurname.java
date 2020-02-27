package by.epam.booking.command.impl.user.change;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.config.MessageManager;
import by.epam.booking.entity.User;
import by.epam.booking.command.Router;
import by.epam.booking.service.user.UserInfoType;
import by.epam.booking.service.user.UserLogic;
import by.epam.booking.type.ParameterName;

import javax.servlet.http.HttpServletRequest;

public class ChangeProfileSurname implements WebCommand {

    private static final String PARAM_VALUE_TO_PAGE = "change";
    private static final String PATH_PAGE = "path.page.user";
    private static final String MESSAGE = "message.usernameEmpty";
    private static final String MESSAGE_SAVE_CHANGED = "message.changed.Save";

    @Override
    public Router execute(HttpServletRequest request) {
        Router page = new Router();
        User transferredUser = new User();

        if(!request.getParameter(ParameterName.PARAM_USER_SURNAME).isEmpty()){
            transferredUser.setLogin((String) request.getSession().getAttribute(ParameterName.PARAM_USER_LOGIN));
            transferredUser.setSurname(request.getParameter(ParameterName.PARAM_USER_SURNAME));
            UserLogic.userUpdate(transferredUser,transferredUser, UserInfoType.SURNAME);
        }else {
            page.setPage(ConfigurationManager.getProperty(PATH_PAGE));
            request.setAttribute(ParameterName.PARAM_TYPE_PROFILE,PARAM_VALUE_TO_PAGE);
            request.getSession().setAttribute(ParameterName.PARAM_SURNAME_ERROR,
                    MessageManager.getProperty(MESSAGE));
            return page;
        }
        page.setPage(ConfigurationManager.getProperty(PATH_PAGE));
        request.getSession().setAttribute(ParameterName.PARAM_TYPE_PROFILE,PARAM_VALUE_TO_PAGE);
        request.getSession().setAttribute(ParameterName.PARAM_USER_SURNAME,transferredUser.getSurname());
        request.getSession().setAttribute(ParameterName.PARAM_SURNAME_ERROR,"");
        request.getSession().setAttribute(ParameterName.PARAM_CHANGED_SAVE,
                MessageManager.getProperty(MESSAGE_SAVE_CHANGED));
        return page;
    }
}
