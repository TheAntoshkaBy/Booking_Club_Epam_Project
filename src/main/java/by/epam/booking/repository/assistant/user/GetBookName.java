package by.epam.booking.repository.assistant.user;

import by.epam.booking.exception.RepositoryException;
import by.epam.booking.repository.assistant.RepositoryHelper;
import by.epam.booking.repository.impl.UserRepository;
import by.epam.booking.specification.impl.user.search.GetUserBookNameByIdBookSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetBookName extends RepositoryHelper {

    private static Logger logger = LogManager.getLogger();

    public static String getBookName(String login) throws RepositoryException {
        String name = null;
        ResultSet userInfo = UserRepository.getINSTANCE().query(new GetUserBookNameByIdBookSpecification(login));
        try {
            userInfo.next();
            name = userInfo.getString("b.name");
        }catch (SQLException e) {

        }finally {
            try {
                closeConnection(userInfo.getStatement().getConnection());
                closeStatement(userInfo.getStatement());
            } catch (SQLException | RepositoryException e) {
               throw new RepositoryException(e);
            }
        }
        return name;
    }
}
