package by.epam.booking.command.impl.guest.book;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.Book;
import by.epam.booking.entity.Comment;
import by.epam.booking.enumeration.PageFormatList;
import by.epam.booking.format.PageFormat;
import by.epam.booking.service.book.BookInfoType;
import by.epam.booking.service.book.BookLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class NewCommentCommand implements WebCommand {
    @Override
    public PageFormat execute(HttpServletRequest request) {

        String commentHeader = request.getParameter("commentHeader");
        String text = request.getParameter("text");
        String login = (String) request.getSession().getAttribute("login");
        Date date = new Date();
        long dateInBd = date.getTime();
        Book book = new Book();
        book.setId(Integer.parseInt(request.getParameter("bookId")));
        book.setBuffDate(dateInBd);
        Comment comment = new Comment(book.getId(),login,text,commentHeader);
        book.setBuffComment(comment);
        BookLogic.bookUpdate(book,book,BookInfoType.ADD_COMMENT);
        System.out.println(comment.getText());
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
