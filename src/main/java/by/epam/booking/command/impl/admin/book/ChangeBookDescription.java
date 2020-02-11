package by.epam.booking.command.impl.admin.book;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.Book;
import by.epam.booking.enumeration.PageFormatList;
import by.epam.booking.format.PageFormat;
import by.epam.booking.service.book.BookInfoType;
import by.epam.booking.service.book.BookLogic;
import com.sun.org.apache.xml.internal.res.XMLErrorResources_tr;

import javax.servlet.http.HttpServletRequest;

public class ChangeBookDescription implements WebCommand {
    @Override
    public PageFormat execute(HttpServletRequest request) {
        PageFormat page = new PageFormat();
        Book book, changedParameter = new Book();
        book = new Book();
        book.setId(Integer.parseInt(request.getParameter("bookId")));
        book = BookLogic.bookGet(book, BookInfoType.ALL);
        assert book != null;
        request.setAttribute("bookId", book.getId());
        request.setAttribute("name",book.getName());
        request.setAttribute("author",book.getAuthor());
        request.setAttribute("description",book.getDescription());
        request.setAttribute("count",book.getCount());
        request.setAttribute("comments", book.getComments());

        if(!request.getParameter("description").isEmpty()){
            book.setDescription(request.getParameter("description"));
            BookLogic.bookUpdate(book,book,BookInfoType.DESCRIPTION);
            request.setAttribute("description",book.getDescription());
        }

        request.getSession().setAttribute("bookSettings","settings");
        page = new PageFormat(PageFormatList.FORWARD, ConfigurationManager.getProperty("path.page.book"));

        return page;
    }
}
