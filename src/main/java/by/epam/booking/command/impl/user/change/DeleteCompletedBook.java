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

public class DeleteCompletedBook implements WebCommand {
    @Override
    public PageFormat execute(HttpServletRequest request) {
        PageFormat page = new PageFormat();

        ArrayList<Integer> booksId = (ArrayList<Integer>) request.getSession().getAttribute("completedBooksId");
        Integer bookId = Integer.parseInt(request.getParameter("bookId"));
        User user = new User();

        user.setLogin((String) request.getSession().getAttribute("login"));
        user.setBookId(bookId);
        UserLogic.userUpdate(user,user, UserInfoType.DELETE_BOOK_COMPLETED);

        booksId.remove(bookId);
        request.getSession().setAttribute("completedBooksId", booksId);
        ArrayList<Book> books = new ArrayList<Book>();
        for(int i = 0; i < booksId.size();i++){
            Book book = new Book();
            book.setId(booksId.get(i));
            books.add(BookLogic.bookGet(book, BookInfoType.ALL));
        }
        request.setAttribute("completedBooks", books);
        request.getSession().setAttribute("type","see");
        page.setPage( ConfigurationManager.getProperty("path.page.completed.books"));
        page.setPageFormat(PageFormatList.FORWARD);
        return page;
    }
}
