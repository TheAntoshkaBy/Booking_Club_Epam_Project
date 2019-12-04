package by.epam.booking.command.impl.guest.book;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.Book;
import by.epam.booking.enumeration.PageFormatList;
import by.epam.booking.format.PageFormat;
import by.epam.booking.logic.book.GetBookInfoLogic;

import javax.servlet.http.HttpServletRequest;

public class ToBookCommand implements WebCommand {
    private Book book;

    @Override
    public PageFormat execute(HttpServletRequest request) {

        book = new Book();
        book.setId(Integer.parseInt(request.getParameter("book")));
        GetBookInfoLogic.getBookById(book);

        request.setAttribute("book", book.getName());
        request.setAttribute("id", book.getId());
        request.setAttribute("name", book.getName());
        request.setAttribute("author",book.getAuthor());
        request.setAttribute("description",book.getDescription());
        request.setAttribute("count",book.getCount());

        PageFormat page = new PageFormat(PageFormatList.FORWARD, ConfigurationManager.getProperty("path.page.book"));
        return page;
    }
}
