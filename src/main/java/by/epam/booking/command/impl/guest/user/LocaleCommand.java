package by.epam.booking.command.impl.guest.user;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.enumeration.PageFormatList;
import by.epam.booking.format.PageFormat;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LocaleCommand implements WebCommand{

    private static final String LOCALE = "locale";
    private static final String RUS = "ru_RU";
    private static final String ENG = "en_US";

    @Override
    public PageFormat execute(HttpServletRequest request) {
        PageFormat page = new PageFormat();
        String locale = (String) request.getSession().getAttribute(LOCALE);
        if (locale.equals(RUS)) {
            request.getSession().setAttribute(LOCALE, ENG) ;
        } else if (locale.equals(ENG)) {
            request.getSession().setAttribute(LOCALE, RUS);
        } else {
        }
        // FIXME: 18.11.2019 Разобраться как сделать так, что бы получать страницу с которой пришел запрос (ставить фильтр на страницу и гетрефером возвращаться).
        page.setPage(ConfigurationManager.getProperty("path.page.index"));
        page.setPageFormat(PageFormatList.FORWARD);
        return page;
    }
}
