package by.epam.booking.repository.assistant.user;

import by.epam.booking.entity.Book;
import by.epam.booking.entity.User;
import by.epam.booking.exception.RepositoryException;
import by.epam.booking.repository.assistant.RepositoryHelper;
import by.epam.booking.repository.impl.BookRepository;
import by.epam.booking.repository.impl.UserRepository;
import by.epam.booking.specification.impl.book.GetAllBooksIdSpecification;
import by.epam.booking.specification.impl.user.search.GetAllUsersIdSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetAllUsersId extends RepositoryHelper {
    private static Logger logger = LogManager.getLogger();

    private static ArrayList<String> users;

    static {
        users = new ArrayList<String>();
    }

    public static ArrayList<String> getAllBooksId() throws RepositoryException {

        ResultSet usersSet = UserRepository.getINSTANCE().query(new GetAllUsersIdSpecification());
        User user;
        users.clear();

        try {
            try {
                while (usersSet.next()) {
                    users.add(usersSet.getString("login"));
                }
            } finally {
                closeConnection(usersSet.getStatement().getConnection());
                closeStatement(usersSet.getStatement());
            }

        } catch (SQLException e) {
            logger.error(e);
            throw new RepositoryException(e);
        }

        return users;
    }

}
