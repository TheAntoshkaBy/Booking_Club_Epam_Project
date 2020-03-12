package by.epam.booking.command.impl.admin.finance;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.Balance;
import by.epam.booking.command.Router;
import by.epam.booking.exception.CommandException;
import by.epam.booking.exception.RepositoryException;
import by.epam.booking.repository.assistant.balance.GetAllBalance;
import by.epam.booking.type.PageChangeType;
import by.epam.booking.type.ParameterName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class ToFinanceListCommand implements WebCommand {

    private static final String PATH_PAGE = "path.page.finance";
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router page;
        ArrayList<Balance> balances = null;
        try {
            balances = GetAllBalance.getAll();
        } catch (RepositoryException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        request.setAttribute(ParameterName.PARAM_LIST_FINANCE_STATE, balances);
        page = new Router(PageChangeType.FORWARD, ConfigurationManager.getPath(PATH_PAGE));
        return page;
    }
}
