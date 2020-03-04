package by.epam.booking.repository.assistant.user;

import by.epam.booking.exception.RepositoryException;
import by.epam.booking.repository.assistant.RepositoryHelper;
import by.epam.booking.repository.impl.UserRepository;
import by.epam.booking.specification.impl.user.search.GetUserProfileImageSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetUserDataString extends RepositoryHelper {

    private static Logger logger = LogManager.getLogger();

    public static String getString(String login) throws RepositoryException {
        String name = null;
        ResultSet userInfo = UserRepository.getINSTANCE().query(new GetUserProfileImageSpecification(login));

        try {

            try {
                userInfo.next();
                name = userInfo.getString("u.profile_image");
            } finally {

                closeConnection(userInfo.getStatement().getConnection());
                closeStatement(userInfo.getStatement());
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RepositoryException(e);
        }

        return name;
    }
}
