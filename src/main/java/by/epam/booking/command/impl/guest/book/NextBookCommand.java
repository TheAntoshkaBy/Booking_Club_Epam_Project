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
import java.io.File;

public class NextBookCommand implements WebCommand {

    public static final String UPLOAD_DIR = "book_image";

    @Override
    public PageFormat execute(HttpServletRequest request) {

        Book book = new Book();
        book.setId(Integer.parseInt( request.getParameter("bookId") ) + 1);
        if(BookLogic.bookGet(book, BookInfoType.ALL) != null){
            request.setAttribute("bookId", book.getId());
            request.setAttribute("name", book.getName());
            request.setAttribute("author",book.getAuthor());
            request.setAttribute("description",book.getDescription());
            request.setAttribute("count",book.getCount());
            request.setAttribute("id", book.getId());
            request.setAttribute("comments", book.getComments());
            request.setAttribute("bookImage",UPLOAD_DIR + File.separator + book.getImage());

        }else{
            book.setId(1);
            BookLogic.bookGet(book,BookInfoType.ALL);
            request.setAttribute("bookId", book.getId());
            request.setAttribute("name", book.getName());
            request.setAttribute("author",book.getAuthor());
            request.setAttribute("description",book.getDescription());
            request.setAttribute("count",book.getCount());
            request.setAttribute("id", book.getId());
            request.setAttribute("comments", book.getComments());
            request.setAttribute("bookImage",UPLOAD_DIR + File.separator + book.getImage());
        }

        PageFormat page = new PageFormat(PageFormatList.FORWARD, ConfigurationManager.getProperty("path.page.book"));
        return page;
    }
}
