package by.epam.booking.repository.assistant.user.change;

import by.epam.booking.repository.assistant.RepositoryHelper;
import by.epam.booking.repository.impl.UserRepository;
import by.epam.booking.specification.Specification;

public class ChangeUserInfo extends RepositoryHelper {
    public static boolean change(Specification specification){
        UserRepository.getINSTANCE().query(specification);
        return true;
    }
}
