package by.epam.booking.command.impl.user.change;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.Book;
import by.epam.booking.entity.User;
import by.epam.booking.enumeration.PageFormatList;
import by.epam.booking.format.PageFormat;
import by.epam.booking.service.book.BookInfoType;
import by.epam.booking.service.book.BookLogic;
import by.epam.booking.service.user.UserInfoType;
import by.epam.booking.service.user.UserLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;

public class PayCommand implements WebCommand {
    @Override
    public PageFormat execute(HttpServletRequest request) {
        PageFormat page = new PageFormat();
        Date date = new Date();
        long dateInBd = date.getTime();
        System.out.println(request.getParameter("currency"));
        System.out.println(Double.parseDouble(request.getParameter("count")));


        User user = new User();
        user.setLogin((String) request.getSession().getAttribute("login"));
        user.setMoneyBalance(Double.parseDouble(request.getParameter("count")));
        user.setBuffMoneyType(request.getParameter("currency"));
        user.setBuffDate(dateInBd);

        UserLogic.userUpdate(user,user, UserInfoType.MONEY_BALANCE);



        request.getSession().setAttribute("type","change");
        page.setPage( ConfigurationManager.getProperty("path.page.user"));
        page.setPageFormat(PageFormatList.FORWARD);
        return page;
    }
}
