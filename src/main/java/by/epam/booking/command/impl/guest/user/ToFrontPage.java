package by.epam.booking.command.impl.guest.user;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.enumeration.PageFormatList;
import by.epam.booking.format.PageFormat;

import javax.servlet.http.HttpServletRequest;

public class ToFrontPage implements WebCommand {
    @Override
    public PageFormat execute(HttpServletRequest request) {
        PageFormat page = new PageFormat();
        page.setPage(ConfigurationManager.getProperty("path.page.index"));
        page.setPageFormat(PageFormatList.FORWARD);
        return page;
    }
}
