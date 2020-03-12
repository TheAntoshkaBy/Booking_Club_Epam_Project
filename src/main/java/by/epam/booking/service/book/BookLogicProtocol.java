package by.epam.booking.service.book;

import by.epam.booking.entity.Book;
import by.epam.booking.exception.ServiceException;

/**
 * The interface Book logic protocol.
 */
public interface BookLogicProtocol {
    /**
     * Book get book.
     *
     * @param transferredBook the transferred book
     * @param types           the types
     * @return the book
     * @throws ServiceException the service exception
     */
    Book bookGet(Book transferredBook, BookInfoType... types) throws ServiceException;

    /**
     * Book update boolean.
     *
     * @param mutableBook       the mutable book
     * @param changeParamOfBook the change param of book
     * @param types             the types
     * @return the boolean
     */
    boolean bookUpdate(Book mutableBook, Book changeParamOfBook, BookInfoType ... types);
}
