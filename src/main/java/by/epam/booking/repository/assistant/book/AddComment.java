package by.epam.booking.repository.assistant.book;

import by.epam.booking.entity.Comment;
import by.epam.booking.repository.impl.UserRepository;
import by.epam.booking.specification.Specification;

public class AddComment {

    private Comment comment;

    public static boolean addComment(Specification specification){
        UserRepository.getINSTANCE().query(specification);
        return true;
    }

}
