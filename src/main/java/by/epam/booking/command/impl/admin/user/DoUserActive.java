package by.epam.booking.command.impl.admin.user;

import by.epam.booking.command.Router;
import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.User;
import by.epam.booking.exception.CommandException;
import by.epam.booking.exception.ServiceException;
import by.epam.booking.service.user.UserInfoType;
import by.epam.booking.service.user.UserLogic;
import by.epam.booking.type.PageChangeType;
import by.epam.booking.type.ParameterName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class DoUserActive extends GoToListOfUsers implements WebCommand {
    private static final String PATH_PAGE = "path.page.users.all";
    private static Logger logger = LogManager.getLogger();


    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        User user = new User(request.getParameter(ParameterName.PARAM_USER_LOGIN));

        try {
            user = UserLogic.userGet(user, UserInfoType.ALL);
            user.setIsActive(true);
            UserLogic.userUpdate(user,user,UserInfoType.IS_ACTIVE);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }

        return goToUserList(request);

    }
}
