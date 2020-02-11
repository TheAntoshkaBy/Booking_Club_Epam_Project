package by.epam.booking.service.book;

import by.epam.booking.entity.Book;
import by.epam.booking.repository.assistant.book.AddComment;
import by.epam.booking.repository.assistant.book.GetBookComments;
import by.epam.booking.repository.assistant.book.GetBookInfo;
import by.epam.booking.repository.assistant.book.GetMaxId;
import by.epam.booking.repository.assistant.book.change.ChangeBook;
import by.epam.booking.specification.impl.book.AddNewBookCommentSpecification;
import by.epam.booking.specification.impl.book.update.UpdateBookAuthorById;
import by.epam.booking.specification.impl.book.update.UpdateBookDescriptionById;
import by.epam.booking.specification.impl.book.update.UpdateBookNameById;


public class BookLogic {
    public static Book bookGet(Book transferredBook, BookInfoType... types){
        Book book = new Book();
        for (BookInfoType type : types) {
            switch (type){
                case ALL:{
                    if(GetBookInfo.getBookById(transferredBook)){
                        transferredBook.setComments(GetBookComments.getAllBooks(transferredBook.getId()));
                        return transferredBook;
                    }else {
                        return null;
                    }

                }
                case NAME:{

                }break;
                case AUTHOR:{

                }break;
                case DESCRIPTION:{

                }break;
                case COUNT:{

                }break;
                case GET_MAX_ID:{
                    book.setId(GetMaxId.getMaxId());
                }break;
                case GET_LIST_COMMENT:{

                }break;

            }
        }
        return book;
    }
    public static boolean bookUpdate(Book mutableBook, Book changeParamOfBook, BookInfoType ... types){
        boolean answer  = false;
        for (BookInfoType type : types) {
            switch (type){
                case ALL:{

                }break;
                case NAME:{
                    ChangeBook.change(new UpdateBookNameById(mutableBook.getId(),changeParamOfBook.getName()));
                }break;
                case AUTHOR:{
                    ChangeBook.change(new UpdateBookAuthorById(mutableBook.getId(),changeParamOfBook.getAuthor()));
                }break;
                case DESCRIPTION:{
                    ChangeBook.change(new UpdateBookDescriptionById(mutableBook.getId(),mutableBook.getDescription()));
                }break;
                case COUNT:{

                }break;
                case ADD_COMMENT:{
                    answer = AddComment.addComment(new AddNewBookCommentSpecification(mutableBook.getBuffComment(),mutableBook.getBuffDate()));
                }break;

            }
        }
        return answer;
    }
}
