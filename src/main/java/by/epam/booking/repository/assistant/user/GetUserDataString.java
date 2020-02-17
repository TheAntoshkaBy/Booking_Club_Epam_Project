package by.epam.booking.repository.assistant.user;

import by.epam.booking.repository.assistant.RepositoryHelper;
import by.epam.booking.repository.impl.UserRepository;
import by.epam.booking.specification.impl.user.search.GetUserBookNameByIdBook;
import by.epam.booking.specification.impl.user.search.GetUserProfileImage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetUserDataString extends RepositoryHelper {
    public static String getString(String login)
    {
        String name = null;
        ResultSet userInfo = UserRepository.getINSTANCE().query(new GetUserProfileImage(login));
        try {
            userInfo.next();
            name = userInfo.getString("u.profile_image");
        }catch (SQLException e) {

        }finally {
            try {
                closeConnection(userInfo.getStatement().getConnection());
                closeStatement(userInfo.getStatement());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return name;
    }
}
