package by.epam.booking.logic.user.changeLogic;

import by.epam.booking.logic.Logic;
import by.epam.booking.repository.impl.UserRepository;
import by.epam.booking.specification.Specification;

import java.sql.SQLException;

public class ChangeSurnameLogic extends Logic {
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
