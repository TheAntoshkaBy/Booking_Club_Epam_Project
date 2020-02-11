package by.epam.booking.command.impl.admin.book;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.config.MessageManager;
import by.epam.booking.entity.Book;
import by.epam.booking.entity.User;
import by.epam.booking.enumeration.PageFormatList;
import by.epam.booking.format.PageFormat;
import by.epam.booking.service.book.BookInfoType;
import by.epam.booking.service.book.BookLogic;
import by.epam.booking.service.user.UserInfoType;
import by.epam.booking.service.user.UserLogic;

import javax.servlet.http.HttpServletRequest;

public class ChangeBookName implements WebCommand {
    @Override
    public PageFormat execute(HttpServletRequest request) {
        PageFormat page = new PageFormat();
        Book book, changedParameter = new Book();
        book = new Book();
        book.setId(Integer.parseInt(request.getParameter("bookId")));
        book = BookLogic.bookGet(book, BookInfoType.ALL);
        assert book != null;
        request.setAttribute("bookId", book.getId());

        request.setAttribute("author",book.getAuthor());
        request.setAttribute("description",book.getDescription());
        request.setAttribute("count",book.getCount());
        request.setAttribute("comments", book.getComments());

        if(!request.getParameter("name").isEmpty()){
            book.setName(request.getParameter("name"));
            BookLogic.bookUpdate(book,book,BookInfoType.NAME);
            request.setAttribute("name", book.getName());
        }

        request.getSession().setAttribute("bookSettings","settings");
        page = new PageFormat(PageFormatList.FORWARD, ConfigurationManager.getProperty("path.page.book"));

        return page;
    }
}
