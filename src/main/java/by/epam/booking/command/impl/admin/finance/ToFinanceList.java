package by.epam.booking.command.impl.admin.finance;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.Balance;
import by.epam.booking.enumeration.PageFormatList;
import by.epam.booking.format.PageFormat;
import by.epam.booking.repository.assistant.balance.GetAllBalance;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class ToFinanceList implements WebCommand {

    private ArrayList<Balance> balances;

    @Override
    public PageFormat execute(HttpServletRequest request) {
        PageFormat page;
        balances = GetAllBalance.getAll();
        request.setAttribute("financeList", balances);
        page = new PageFormat(PageFormatList.FORWARD, ConfigurationManager.getProperty("path.page.finance"));
        return page;
    }
}
