package by.epam.booking.repository.assistant.user.changeLogic;

import by.epam.booking.connection.ConnectionPool;
import by.epam.booking.repository.assistant.RepositoryHelper;
import by.epam.booking.repository.impl.UserRepository;
import by.epam.booking.specification.Specification;

import java.sql.SQLException;

public class ChangeBookId extends RepositoryHelper {
    public static boolean changeBookId(Specification specification){
        UserRepository.getINSTANCE().query(specification);
        return true;
    }
}
