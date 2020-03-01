package by.epam.booking.specification.impl.book;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.entity.Comment;
import by.epam.booking.exception.ConnectionPoolException;
import by.epam.booking.exception.SpecificationException;
import by.epam.booking.specification.Specification;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddNewBookCommentSpecification implements Specification {
    private static final String SQL_INSERT_USER =
            "INSERT INTO Booking_Club.Comments " +
                    "(date, author, bookId, text, header) VALUES (?,?,?,?,?)";
    private Comment comment;
    private long date;

    public AddNewBookCommentSpecification(Comment comment, long date) {
        this.comment = comment;
        this.date = date;
    }

    @Override
    public PreparedStatement specify() throws SpecificationException {
        PreparedStatement preparedStatement = null;
        try {

            preparedStatement = ConnectionPool.getInstance().getConnection().prepareStatement(SQL_INSERT_USER);
        } catch (SQLException | ConnectionPoolException e) {
            throw new SpecificationException(e);
        }
        try {
            preparedStatement.setLong(1, date);
            preparedStatement.setString(2, comment.getAuthor());
            preparedStatement.setInt(3, comment.getBookId());
            preparedStatement.setString(4,comment.getText());
            preparedStatement.setString(5,comment.getHeader());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

       return preparedStatement;
    }

    @Override
    public boolean isUpdate() {
        return true;
    }
}
