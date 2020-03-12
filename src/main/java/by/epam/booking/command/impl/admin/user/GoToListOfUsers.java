package by.epam.booking.command.impl.admin.user;

import by.epam.booking.command.Router;
import by.epam.booking.command.WebCommand;
import by.epam.booking.command.impl.BookingClubCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.User;
import by.epam.booking.exception.CommandException;
import by.epam.booking.exception.ServiceException;
import by.epam.booking.service.user.UserInfoType;
import by.epam.booking.type.PageChangeType;
import by.epam.booking.type.ParameterName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class GoToListOfUsers extends BookingClubCommand implements WebCommand {

    private static final String PATH_PAGE = "path.page.users.all";
    private static Logger logger = LogManager.getLogger();
    private static String activeUserStatus = "Active";
    private static String blockedUserStatus = "Blocked";

    public Router goToUserList(HttpServletRequest request) throws CommandException{
        ArrayList<User> users = null;
        try {
            users = userLogic.userGetAll(UserInfoType.GET_ALL_USERS);
            users.forEach(user -> {
                if(user.getIsActive()){
                    user.setStatus(activeUserStatus);
                }else {
                    user.setStatus(blockedUserStatus);
                }
            });
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        request.getSession().setAttribute(ParameterName.PARAM_ALL_USERS, users);

        return new Router(PageChangeType.FORWARD, ConfigurationManager.getPath(PATH_PAGE));

    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        Router page = goToUserList(request);

        return page;

    }
}
