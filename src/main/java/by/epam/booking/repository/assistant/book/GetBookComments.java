package by.epam.booking.repository.assistant.book;

import by.epam.booking.entity.Comment;
import by.epam.booking.exception.RepositoryException;
import by.epam.booking.repository.assistant.RepositoryHelper;
import by.epam.booking.repository.impl.BookRepository;
import by.epam.booking.specification.impl.book.GetAllCommentsBookSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class GetBookComments extends RepositoryHelper {

    private static Logger logger = LogManager.getLogger();
    private static ArrayList<Comment> comments;

    static {
        comments = new ArrayList<Comment>();
    }

    public static ArrayList<Comment> getAllBooks(int bookId) throws RepositoryException{
        ResultSet bookSet = BookRepository.getInstance().query(new GetAllCommentsBookSpecification(bookId));
        Comment comment;
        comments.clear();

        try {
            try {
                while (bookSet.next()) {
                    comment = new Comment();
                    comment.setAuthor(bookSet.getString("c.author"));
                    Date date = new Date(bookSet.getLong("c.date"));
                    comment.setDate(date);
                    comment.setText(bookSet.getString("c.text"));
                    comment.setHeader(bookSet.getString("c.header"));
                    comments.add(comment);
                }
            } finally {
                closeConnection(bookSet.getStatement().getConnection());
                closeStatement(bookSet.getStatement());
            }
        }catch (SQLException e){
            logger.error(e);
            throw new RepositoryException(e);
        }


        return comments;
    }

    public static ArrayList<Comment> getBooks() {
        return comments;
    }
}
