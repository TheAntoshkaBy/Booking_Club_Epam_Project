package by.epam.booking.repository.assistant.user.changeLogic;

import by.epam.booking.repository.assistant.RepositoryHelper;
import by.epam.booking.repository.impl.UserRepository;
import by.epam.booking.specification.Specification;

import java.sql.SQLException;

public class ChangeUsername extends RepositoryHelper {
    public static boolean changeUsername(Specification specification){
        UserRepository.getINSTANCE().query(specification);
       try {
           closeConnection(UserRepository.getINSTANCE().getStatement().getConnection());
           closeStatement(UserRepository.getINSTANCE().getStatement());
       }catch (SQLException e){
           e.printStackTrace();
       }
        return true;
    }
}
