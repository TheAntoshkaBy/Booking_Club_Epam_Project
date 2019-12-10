package by.epam.booking.command.impl.guest.book;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.Book;
import by.epam.booking.enumeration.PageFormatList;
import by.epam.booking.format.PageFormat;
import by.epam.booking.repository.assistant.book.GetBookInfo;
import by.epam.booking.repository.assistant.book.GetMaxId;
import by.epam.booking.service.book.BookInfoType;
import by.epam.booking.service.book.BookLogic;

import javax.servlet.http.HttpServletRequest;

public class PreviousBookCommand implements WebCommand {
    @Override
    public PageFormat execute(HttpServletRequest request) {

        Book book = new Book();
        book.setId(Integer.parseInt( request.getParameter("bookId") ) - 1);
        if(BookLogic.bookGet(book, BookInfoType.ALL) != null){
            request.setAttribute("bookId", book.getId());
            request.setAttribute("name", book.getName());
            request.setAttribute("author",book.getAuthor());
            request.setAttribute("description",book.getDescription());
            request.setAttribute("count",book.getCount());

        }else{
            Book endBook = new Book();
            endBook = BookLogic.bookGet(endBook, BookInfoType.GET_MAX_ID);
            book.setId(endBook.getId());
            GetBookInfo.getBookById(book);
            request.setAttribute("bookId", book.getId());
            request.setAttribute("name", book.getName());
            request.setAttribute("author",book.getAuthor());
            request.setAttribute("description",book.getDescription());
            request.setAttribute("count",book.getCount());
        }

        PageFormat page = new PageFormat(PageFormatList.FORWARD, ConfigurationManager.getProperty("path.page.book"));
        return page;
    }
}
