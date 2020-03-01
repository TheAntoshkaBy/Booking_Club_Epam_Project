package by.epam.booking.command.impl.admin.finance;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.Balance;
import by.epam.booking.command.Router;
import by.epam.booking.exception.RepositoryException;
import by.epam.booking.repository.assistant.balance.GetAllBalance;
import by.epam.booking.type.PageChangeType;
import by.epam.booking.type.ParameterName;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;

public class ToFinanceListCommand implements WebCommand {

    private static final String PATH_PAGE = "path.page.finance";

    @Override
    public Router execute(HttpServletRequest request) throws SQLException, RepositoryException {
        Router page;
        ArrayList<Balance> balances = GetAllBalance.getAll();
        request.setAttribute(ParameterName.PARAM_LIST_FINANCE_STATE, balances);
        page = new Router(PageChangeType.FORWARD, ConfigurationManager.getProperty(PATH_PAGE));
        return page;
    }
}
