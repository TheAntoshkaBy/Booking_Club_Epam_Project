package by.epam.booking.repository.assistant.user.changeLogic;

import by.epam.booking.repository.assistant.RepositoryHelper;
import by.epam.booking.repository.impl.UserRepository;
import by.epam.booking.specification.Specification;

public class ChangeUser extends RepositoryHelper {
    public static boolean change(Specification specification){
        UserRepository.getINSTANCE().query(specification);
        return true;
    }
}