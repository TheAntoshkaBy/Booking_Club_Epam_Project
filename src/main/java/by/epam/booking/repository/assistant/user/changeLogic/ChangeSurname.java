package by.epam.booking.repository.assistant.user.changeLogic;

import by.epam.booking.repository.assistant.RepositoryHelper;
import by.epam.booking.repository.impl.UserRepository;
import by.epam.booking.specification.Specification;

import java.sql.SQLException;

public class ChangeSurname extends RepositoryHelper {
    public static boolean changeSurname(Specification specification){
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
