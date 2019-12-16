package by.epam.booking.command.impl.guest.book;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.Book;
import by.epam.booking.enumeration.PageFormatList;
import by.epam.booking.format.PageFormat;
import by.epam.booking.repository.assistant.book.GetBookInfo;
import by.epam.booking.service.book.BookInfoType;
import by.epam.booking.service.book.BookLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class ToBookCommand implements WebCommand {
    private Book book;

    @Override
    public PageFormat execute(HttpServletRequest request) {

        book = new Book();
        book.setId(Integer.parseInt(request.getParameter("bookId")));
        book = BookLogic.bookGet(book, BookInfoType.ALL);
        assert book != null;
        request.setAttribute("bookId", book.getId());
        request.setAttribute("name", book.getName());
        request.setAttribute("author",book.getAuthor());
        request.setAttribute("description",book.getDescription());
        request.setAttribute("count",book.getCount());
        request.setAttribute("comments", book.getComments());
        PageFormat page = new PageFormat(PageFormatList.FORWARD, ConfigurationManager.getProperty("path.page.book"));
        return page;
    }
}
