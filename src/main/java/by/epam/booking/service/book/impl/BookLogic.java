package by.epam.booking.service.book.impl;

import by.epam.booking.entity.Book;
import by.epam.booking.exception.RepositoryException;
import by.epam.booking.exception.ServiceException;
import by.epam.booking.repository.assistant.book.AddComment;
import by.epam.booking.repository.assistant.book.GetBookComments;
import by.epam.booking.repository.assistant.book.GetBookInfo;
import by.epam.booking.repository.assistant.book.GetMaxId;
import by.epam.booking.repository.assistant.book.change.ChangeBook;
import by.epam.booking.service.book.BookInfoType;
import by.epam.booking.service.book.BookLogicProtocol;
import by.epam.booking.specification.impl.book.AddNewBookCommentSpecification;
import by.epam.booking.specification.impl.book.update.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;


public class BookLogic implements BookLogicProtocol {

    private static BookLogic INSTANCE;
    private static AtomicBoolean instanceCreated = new AtomicBoolean();
    private static final ReentrantLock getInstanceLock = new ReentrantLock();
    private static Logger logger = LogManager.getLogger();
    private BookLogic(){
    }

    public static BookLogic getInstance() {
        if (!instanceCreated.get()) {
            getInstanceLock.lock();
            try {
                if (null == INSTANCE) {
                    INSTANCE = new BookLogic();
                    instanceCreated.set(true);
                }
            } finally {
                getInstanceLock.unlock();
            }
        }
        return INSTANCE;
    }

    public Book bookGet(Book transferredBook, BookInfoType... types) throws ServiceException {
        Book book = new Book();
        for (BookInfoType type : types) {
            switch (type){
                case ALL:{
                    try {
                        if(GetBookInfo.getBookById(transferredBook)){
                            transferredBook.setComments(GetBookComments.getAllBooks(transferredBook.getId()));
                            transferredBook.setImage(GetBookInfo.getBookImageById(transferredBook));
                            return transferredBook;
                        }else {
                            return null;
                        }
                    } catch (RepositoryException e) {
                        logger.error(e);
                        throw new ServiceException(e);
                    }

                }
                case GET_MAX_ID:{
                    try {
                        book.setId(GetMaxId.getMaxId());
                    } catch ( RepositoryException e) {
                        logger.error(e);
                        throw new ServiceException(e);
                    }
                }break;
                case ALL_ID:{

                }break;

            }
        }
        return book;
    }
    public boolean bookUpdate(Book mutableBook, Book changeParamOfBook, BookInfoType ... types){
        boolean answer  = false;
        for (BookInfoType type : types) {
            switch (type){
                case ALL:{

                }break;
                case NAME:{
                    ChangeBook.change(new UpdateBookNameByIdSpecification(mutableBook.getId(),changeParamOfBook.getName()));
                }break;
                case AUTHOR:{
                    ChangeBook.change(new UpdateBookAuthorByIdSpecification(mutableBook.getId(),changeParamOfBook.getAuthor()));
                }break;
                case DESCRIPTION:{
                    ChangeBook.change(new UpdateBookDescriptionByIdSpecification(mutableBook.getId(),mutableBook.getDescription()));
                }break;
                case ADD_COMMENT:{
                    answer = AddComment.addComment(new AddNewBookCommentSpecification(mutableBook.getBuffComment(),mutableBook.getBuffDate()));
                }break;
                case DELETE:{
                    answer = ChangeBook.change(new DeleteBookSpecification(mutableBook.getId()));
                }break;
                case COUNT:{
                    answer = ChangeBook.change(new BookChangeCountSpecification(mutableBook.getId(),mutableBook.getCount()));
                }break;
                case IMAGE:{
                    answer = ChangeBook.change(new BookChangeImageSpecification(mutableBook.getId(),mutableBook.getImage()));
                }break;

            }
        }
        return answer;
    }
}
