package by.epam.booking.repository.assistant.user.changeLogic;

import by.epam.booking.repository.assistant.RepositoryHelper;
import by.epam.booking.repository.impl.UserRepository;
import by.epam.booking.specification.Specification;

public class ChangeReadingPlanId extends RepositoryHelper {
    public static boolean changeReadingPlanId(Specification specification){
        UserRepository.getINSTANCE().query(specification);
        return true;
    }
}
