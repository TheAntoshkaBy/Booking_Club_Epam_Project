package by.epam.booking.repository.assistant.user;

import by.epam.booking.exception.RepositoryException;
import by.epam.booking.repository.assistant.RepositoryHelper;
import by.epam.booking.repository.impl.UserRepository;
import by.epam.booking.specification.impl.user.search.GetUserProfileImageSpecification;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetUserDataString extends RepositoryHelper {
    public static String getString(String login) throws RepositoryException {
        String name = null;
        ResultSet userInfo = UserRepository.getINSTANCE().query(new GetUserProfileImageSpecification(login));
        try {
            userInfo.next();
            name = userInfo.getString("u.profile_image");
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
