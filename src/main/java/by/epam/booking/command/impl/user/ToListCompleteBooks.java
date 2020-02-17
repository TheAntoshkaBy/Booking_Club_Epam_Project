package by.epam.booking.command.impl.user;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.Book;
import by.epam.booking.enumeration.PageFormatList;
import by.epam.booking.format.PageFormat;
import by.epam.booking.service.book.BookInfoType;
import by.epam.booking.service.book.BookLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class ToListCompleteBooks implements WebCommand {
    @Override
    public PageFormat execute(HttpServletRequest request) {
        PageFormat page = new PageFormat();
        ArrayList<Integer> idBooks = (ArrayList<Integer>) request.getSession().getAttribute("completedBooksId");
        ArrayList<Book> books = new ArrayList<Book>();
        for(int i = 0; i < idBooks.size();i++){
            Book book = new Book();
            book.setId(idBooks.get(i));
            books.add(BookLogic.bookGet(book,BookInfoType.ALL));
        }
        request.setAttribute("completedBooks", books);
        request.getSession().setAttribute("type","see");
        page.setPage( ConfigurationManager.getProperty("path.page.completed.books"));
        page.setPageFormat(PageFormatList.FORWARD);
        return page;
    }
}
