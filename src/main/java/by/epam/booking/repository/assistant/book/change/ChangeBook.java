package by.epam.booking.repository.assistant.book.change;

import by.epam.booking.repository.assistant.RepositoryHelper;
import by.epam.booking.repository.impl.UserRepository;
import by.epam.booking.specification.Specification;

public class ChangeBook extends RepositoryHelper {
    public static boolean change(Specification specification){
        UserRepository.getINSTANCE().query(specification);
        return true;
    }
}
