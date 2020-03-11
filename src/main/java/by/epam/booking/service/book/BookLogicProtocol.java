package by.epam.booking.service.book;

import by.epam.booking.entity.Book;
import by.epam.booking.exception.ServiceException;

public interface BookLogicProtocol {
    Book bookGet(Book transferredBook, BookInfoType... types) throws ServiceException;
    boolean bookUpdate(Book mutableBook, Book changeParamOfBook, BookInfoType ... types);
}
