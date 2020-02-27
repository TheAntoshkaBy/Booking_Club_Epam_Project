package by.epam.booking.command.impl.user.change;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.User;
import by.epam.booking.type.PageChangeType;
import by.epam.booking.command.Router;
import by.epam.booking.service.user.UserInfoType;
import by.epam.booking.service.user.UserLogic;
import by.epam.booking.type.ParameterName;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class PayCommand implements WebCommand {

    private static final String PARAM_VALUE_TO_PAGE = "change";
    private static final String PATH_PAGE = "path.page.user";

    @Override
    public Router execute(HttpServletRequest request) {
        Router page = new Router();
        Date date = new Date();
        long dateInBd = date.getTime();
        System.out.println(request.getParameter(ParameterName.PARAM_FINANCE_CURRENCY));
        System.out.println(Double.parseDouble(request.getParameter(ParameterName.PARAM_BOOK_COUNT)));


        User user = new User();
        user.setLogin((String) request.getSession().getAttribute(ParameterName.PARAM_USER_LOGIN));
        user.setMoneyBalance(Double.parseDouble(request.getParameter(ParameterName.PARAM_BOOK_COUNT)));
        user.setBuffMoneyType(request.getParameter(ParameterName.PARAM_FINANCE_CURRENCY));
        user.setBuffDate(dateInBd);

        UserLogic.userUpdate(user,user, UserInfoType.MONEY_BALANCE);



        request.getSession().setAttribute(ParameterName.PARAM_TYPE_PROFILE,PARAM_VALUE_TO_PAGE);
        page.setPage( ConfigurationManager.getProperty(PATH_PAGE));
        page.setPageFormat(PageChangeType.FORWARD);
        return page;
    }
}
