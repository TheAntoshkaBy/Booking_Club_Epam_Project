package by.epam.booking.command.impl.user;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.Book;
import by.epam.booking.entity.Comment;
import by.epam.booking.command.Router;
import by.epam.booking.service.book.BookInfoType;
import by.epam.booking.service.book.BookLogic;
import by.epam.booking.type.PageChangeType;
import by.epam.booking.type.ParameterName;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class NewCommentCommand implements WebCommand {

    private static final String PATH_PAGE = "path.page.book";

    @Override
    public Router execute(HttpServletRequest request) {

        String commentHeader = request.getParameter(ParameterName.COMMENT_HEADER);
        String text = request.getParameter(ParameterName.COMMENT_BODY_TEXT);
        String login = (String) request.getSession().getAttribute(ParameterName.PARAM_USER_LOGIN);
        Date date = new Date();
        long dateInBd = date.getTime();
        Book book = new Book();
        book.setId(Integer.parseInt(request.getParameter(ParameterName.PARAM_BOOK_ID)));
        book.setBuffDate(dateInBd);
        Comment comment = new Comment(book.getId(), login, text, commentHeader);
        book.setBuffComment(comment);
        BookLogic.bookUpdate(book, book, BookInfoType.ADD_COMMENT);
        System.out.println(comment.getText());
        book = BookLogic.bookGet(book, BookInfoType.ALL);
        assert book != null;
        request.setAttribute(ParameterName.PARAM_BOOK_ID, book.getId());
        request.setAttribute(ParameterName.BOOK_NAME_PARAMETER, book.getName());
        request.setAttribute(ParameterName.PARAM_BOOK_AUTHOR, book.getAuthor());
        request.setAttribute(ParameterName.PARAM_BOOK_DESCRIPTION, book.getDescription());
        request.setAttribute(ParameterName.PARAM_BOOK_COUNT, book.getCount());
        request.setAttribute(ParameterName.PARAM_BOOK_COMMENTS, book.getComments());
        Router page = new Router(PageChangeType.FORWARD, ConfigurationManager.getProperty(PATH_PAGE));
        return page;
    }
}
