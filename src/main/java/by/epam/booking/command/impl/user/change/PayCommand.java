package by.epam.booking.command.impl.user.change;

import by.epam.booking.command.WebCommand;
import by.epam.booking.command.impl.BookingClubCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.User;
import by.epam.booking.exception.CommandException;
import by.epam.booking.exception.ServiceException;
import by.epam.booking.type.PageChangeType;
import by.epam.booking.command.Router;
import by.epam.booking.service.user.UserInfoType;
import by.epam.booking.service.user.impl.UserLogic;
import by.epam.booking.type.ParameterName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class PayCommand extends BookingClubCommand implements WebCommand {

    private static final String PARAM_VALUE_TO_PAGE = "see";
    private static final String PATH_PAGE = "path.page.user";
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router page = new Router();
        Date date = new Date();
        long dateInBd = date.getTime();

        User user = new User();
        user.setLogin((String) request.getSession().getAttribute(ParameterName.PARAM_USER_LOGIN));
        user.setMoneyBalance(Double.parseDouble(request.getParameter(ParameterName.PARAM_BOOK_COUNT)));
        user.setBuffMoneyType(request.getParameter(ParameterName.PARAM_FINANCE_CURRENCY));
        user.setBuffDate(dateInBd);

        try {
            userLogic.userUpdate(user,user, UserInfoType.MONEY_BALANCE);
            user = userLogic.userGet(user,UserInfoType.ALL);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        request.getSession().setAttribute(ParameterName.PARAM_FINANCE_MONEY,user.getMoneyBalance());
        request.getSession().setAttribute(ParameterName.PARAM_TYPE_PROFILE,PARAM_VALUE_TO_PAGE);
        logger.debug("User balance get " + user.getMoneyBalance());
        page.setPage( ConfigurationManager.getProperty(PATH_PAGE));
        page.setPageFormat(PageChangeType.REDIRECT);
        return page;
    }
}
