package by.epam.booking.specification.impl.user.update;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateProfileImage implements Specification {
    private String userLogin;
    private String profileImage;
    private String SQL_REQUEST = "UPDATE "+USER_TABLE+" SET profile_image=? WHERE login=?";

    public UpdateProfileImage(String userLogin, String profileImage) {
        this.userLogin = userLogin;
        this.profileImage = profileImage;
    }

    @Override
    public PreparedStatement specify() throws SQLException {
        PreparedStatement statement = null;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_REQUEST);
            statement.setString(1,profileImage);
            statement.setString(2,userLogin);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    @Override
    public boolean isUpdate() {
        return true;
    }
}