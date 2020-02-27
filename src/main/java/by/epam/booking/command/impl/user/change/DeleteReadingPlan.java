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

public class DeleteReadingPlan implements WebCommand {

    private static final String PARAM_VALUE_TO_PAGE = "change";
    private static final String PATH_PAGE = "path.page.user";
    private static final String MESSAGE_SAVE_CHANGED = "message.changed.Save";

    @Override
    public Router execute(HttpServletRequest request) {
        Router page = new Router();
        User user = new User();
        user.setLogin((String) request.getSession().getAttribute(ParameterName.PARAM_USER_LOGIN));
        user.setReadingPlanId(null);
        if(UserLogic.userUpdate(user,user,UserInfoType.DELETE_READING_PLAN)){
            request.getSession().setAttribute(ParameterName.PARAM_NAME_OF_READING_PLAN,null);
            request.getSession().setAttribute(ParameterName.PARAM_READING_PLAN_ID,null);
        }
        page.setPage(ConfigurationManager.getProperty(PATH_PAGE));
        request.setAttribute(ParameterName.PARAM_TYPE_PROFILE,PARAM_VALUE_TO_PAGE);
        request.getSession().setAttribute(ParameterName.PARAM_CHANGE_SAVED, MessageManager
                .getProperty(MESSAGE_SAVE_CHANGED));
        return page;
    }
}
