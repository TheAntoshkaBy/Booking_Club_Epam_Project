package by.epam.booking.command.impl.guest.user.message;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.command.Router;
import by.epam.booking.type.PageChangeType;
import by.epam.booking.type.ParameterName;

import javax.servlet.http.HttpServletRequest;

public class SendToAdminCommand implements WebCommand {

    private final String PATH_PAGE = "path.page.user.passive";
    private final String EMAIL_TO = "vedenichev.work.akk@gmail.com";

    @Override
    public Router execute(HttpServletRequest request) {
        Router page = new Router();

        MailThread mailOperator = new MailThread(
                EMAIL_TO,
                request.getParameter(ParameterName.PARAM_THEME),
                request.getParameter(ParameterName.PARAM_MESSAGE),
                (String) request.getSession().getAttribute(ParameterName.PARAM_USER_LOGIN_INTERIM));

        mailOperator.start();

        page.setPage(ConfigurationManager.getPath(PATH_PAGE));
        page.setPageFormat(PageChangeType.REDIRECT);
        return page;
    }
}
