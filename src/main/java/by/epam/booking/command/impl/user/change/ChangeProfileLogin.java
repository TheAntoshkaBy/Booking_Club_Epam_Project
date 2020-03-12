package by.epam.booking.command.impl.user.change;

import by.epam.booking.command.WebCommand;
import by.epam.booking.command.impl.BookingClubCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.User;
import by.epam.booking.command.Router;
import by.epam.booking.exception.CommandException;
import by.epam.booking.exception.ServiceException;
import by.epam.booking.service.user.UserInfoType;
import by.epam.booking.type.PageChangeType;
import by.epam.booking.type.ParameterName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ChangeProfileLogin extends BookingClubCommand implements WebCommand {

    private static final String PATH_PAGE_USER = "path.page.user";
    private static final String PARAM_TYPE_VALUE = "change";
    private static final String MESSAGE_ABOUT_CONSIST_LOGIN = "message.login.consist";
    private static final String MESSAGE_ABOUT_CHANGED_SAVE = "message.changed.Save";
    private static final String MESSAGE_ABOUT_EMPTY_LOGIN = "message.loginEmpty";
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException  {
        Router page = new Router();

        if (!request.getParameter(ParameterName.PARAM_USER_LOGIN).isEmpty()) {
            User mutableUser = new User(), changeParamOfUser = new User();
            mutableUser.setLogin((String) request.getSession().getAttribute(ParameterName.PARAM_USER_LOGIN));
            changeParamOfUser.setLogin(request.getParameter(ParameterName.PARAM_USER_LOGIN));
            try {
                if (userLogic.userUpdate(mutableUser, changeParamOfUser, UserInfoType.LOGIN)) {
                    request.getSession().setAttribute(ParameterName.PARAM_USER_LOGIN,
                            request.getParameter(ParameterName.PARAM_USER_LOGIN));

                    page.setPage(ConfigurationManager.getPath(PATH_PAGE_USER));
                    page.setPageFormat(PageChangeType.REDIRECT);
                    request.getSession().setAttribute(ParameterName.PARAM_TYPE_PROFILE, PARAM_TYPE_VALUE);
                    request.getSession().setAttribute(ParameterName.PARAM_USER_LOGIN, changeParamOfUser.getLogin());
                    request.getSession().setAttribute(ParameterName.PARAM_USERNAME_ERROR, "");
                    request.getSession().setAttribute(ParameterName.PARAM_CHANGE_SAVED,
                            ConfigurationManager.getMessageProperty(MESSAGE_ABOUT_CHANGED_SAVE));
                    logger.debug("Login changed!");
                } else {
                    page.setPage(ConfigurationManager.getPath(PATH_PAGE_USER));
                    request.setAttribute(ParameterName.PARAM_TYPE_PROFILE, PARAM_TYPE_VALUE);
                    request.getSession().setAttribute(ParameterName.PARAM_USERNAME_ERROR,
                            ConfigurationManager.getMessageProperty(MESSAGE_ABOUT_CONSIST_LOGIN));
                    return page;
                }
            } catch (ServiceException e) {
                logger.error(e);
                throw new CommandException(e);
            }
        } else {
            page.setPage(ConfigurationManager.getPath(PATH_PAGE_USER));
            request.getSession().setAttribute(ParameterName.PARAM_TYPE_PROFILE, PARAM_TYPE_VALUE);
            request.getSession().setAttribute(ParameterName.PARAM_USERNAME_ERROR,
                    ConfigurationManager.getMessageProperty(MESSAGE_ABOUT_EMPTY_LOGIN));
            return page;
        }

        return page;

    }
}
