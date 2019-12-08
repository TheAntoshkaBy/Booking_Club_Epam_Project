package by.epam.booking.command.impl.guest.book;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.Book;
import by.epam.booking.enumeration.PageFormatList;
import by.epam.booking.format.PageFormat;
import by.epam.booking.repository.assistant.book.GetBookInfo;
import by.epam.booking.repository.assistant.book.GetMaxId;

import javax.servlet.http.HttpServletRequest;

public class PreviousBookCommand implements WebCommand {
    @Override
    public PageFormat execute(HttpServletRequest request) {

        Book book = new Book();
        book.setId(Integer.parseInt(request.getParameter("book")) - 1);
        if(GetBookInfo.getBookById(book)){
            request.setAttribute("book", book.getName());
            request.setAttribute("name", book.getName());
            request.setAttribute("author",book.getAuthor());
            request.setAttribute("description",book.getDescription());
            request.setAttribute("count",book.getCount());
            request.setAttribute("id", book.getId());

        }else{
            book.setId(GetMaxId.getMaxId());
            GetBookInfo.getBookById(book);
            request.setAttribute("book", book.getName());
            request.setAttribute("name", book.getName());
            request.setAttribute("author",book.getAuthor());
            request.setAttribute("description",book.getDescription());
            request.setAttribute("count",book.getCount());
            request.setAttribute("id", book.getId());
        }

        PageFormat page = new PageFormat(PageFormatList.FORWARD, ConfigurationManager.getProperty("path.page.book"));
        return page;
    }
}
