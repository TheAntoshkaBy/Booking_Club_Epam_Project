package by.epam.booking.command.impl.user.change;

import by.epam.booking.command.Router;
import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.ReadingPlan;
import by.epam.booking.entity.User;
import by.epam.booking.exception.CommandException;
import by.epam.booking.exception.ServiceException;
import by.epam.booking.service.plan.ReadingPlanInfoType;
import by.epam.booking.service.plan.ReadingPlanLogic;
import by.epam.booking.service.user.UserInfoType;
import by.epam.booking.service.user.UserLogic;
import by.epam.booking.type.PageChangeType;
import by.epam.booking.type.ParameterName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteUserReadingPlan implements WebCommand {


    private static String PATH_PAGE = "path.page.user";
    private static Logger logger = LogManager.getLogger();
    public static final String EMPTY_NAME = "--";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        User user = new User();
        user.setLogin((String) request.getSession().getAttribute(ParameterName.PARAM_USER_LOGIN));
        user.setReadingPlanId(null);
        try {
            UserLogic.userUpdate(user,user, UserInfoType.READING_PLAN);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }

        request.getSession().setAttribute(ParameterName.PARAM_NAME_OF_READING_PLAN, EMPTY_NAME);

        return new Router(PageChangeType.REDIRECT, ConfigurationManager.getProperty(PATH_PAGE));
    }
}
