package by.epam.booking.command.impl.guest.user;

import by.epam.booking.command.Router;
import by.epam.booking.command.WebCommand;
import by.epam.booking.command.impl.BookingClubCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.User;
import by.epam.booking.exception.CommandException;
import by.epam.booking.exception.ServiceException;
import by.epam.booking.service.user.CodeGenerator;
import by.epam.booking.service.user.UserInfoType;
import by.epam.booking.type.PageChangeType;
import by.epam.booking.type.ParameterName;

import javax.servlet.http.HttpServletRequest;

public class ConfirmCommand extends BookingClubCommand implements WebCommand {

    public static final String MESSAGE = "message.confirmError";
    private static final String PATH_PAGE = "path.page.user.confirmation";
    private static final String PATH_PAGE_LOGIN = "path.page.login";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router page = new Router();

        String code = request.getParameter(ParameterName.PARAM_CONFIRM_CODE);
        if (CodeGenerator.GENERATOR.codeConfirm(request.getSession().getId(), code)) {
            User user = new User();
            user.setLogin((String) request.getSession().getAttribute(ParameterName.PARAM_USER_LOGIN_INTERIM));
            user.setIsActive(true);
            try {
                userLogic.userUpdate(user, user, UserInfoType.IS_ACTIVE);
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
            page.setPageFormat(PageChangeType.REDIRECT);
            request.getSession().setAttribute(ParameterName.PARAM_CONFIRM_ERROR, null);
            page.setPage(ConfigurationManager.getPath(PATH_PAGE_LOGIN));
        } else {
            page.setPageFormat(PageChangeType.REDIRECT);
            request.getSession().setAttribute(ParameterName.PARAM_CONFIRM_ERROR, ConfigurationManager.getMessageProperty(MESSAGE));
            page.setPage(ConfigurationManager.getPath(PATH_PAGE));
        }

        return page;
    }
}
