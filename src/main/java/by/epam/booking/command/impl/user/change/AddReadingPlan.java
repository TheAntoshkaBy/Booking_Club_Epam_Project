package by.epam.booking.command.impl.user.change;

import by.epam.booking.command.Router;
import by.epam.booking.command.WebCommand;
import by.epam.booking.command.impl.BookingClubCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.ReadingPlan;
import by.epam.booking.entity.User;
import by.epam.booking.exception.CommandException;
import by.epam.booking.exception.ServiceException;
import by.epam.booking.service.plan.ReadingPlanInfoType;
import by.epam.booking.service.user.UserInfoType;
import by.epam.booking.type.PageChangeType;
import by.epam.booking.type.ParameterName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddReadingPlan extends BookingClubCommand implements WebCommand {

    private static String PATH_PAGE = "path.page.plan.book";
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        User user = new User();
        user.setLogin((String) request.getSession().getAttribute(ParameterName.PARAM_USER_LOGIN));
        user.setReadingPlanId((Integer) request.getSession().getAttribute(ParameterName.PARAM_READING_PLAN_ID_INTERIM));

        try {
            userLogic.userUpdate(user,user, UserInfoType.READING_PLAN);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        ReadingPlan readingPlan = new ReadingPlan();
        readingPlan.setIdReadingPlan(user.getReadingPlanId());
        try {
            readingPlanLogic.planGet(readingPlan, ReadingPlanInfoType.READING_PLAN_NAME);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        request.getSession().setAttribute(ParameterName.PARAM_NAME_OF_READING_PLAN, readingPlan.getName());

        Router page = new Router(PageChangeType.REDIRECT, ConfigurationManager.getPath(PATH_PAGE));
        return page;
    }

}
