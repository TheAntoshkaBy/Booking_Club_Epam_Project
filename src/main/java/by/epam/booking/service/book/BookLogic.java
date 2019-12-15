package by.epam.booking.service.book;

import by.epam.booking.entity.Book;
import by.epam.booking.repository.assistant.book.GetBookComments;
import by.epam.booking.repository.assistant.book.GetBookInfo;
import by.epam.booking.repository.assistant.book.GetMaxId;
import by.epam.booking.specification.impl.book.GetAllCommentsBook;


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

                }
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

                }break;
                case AUTHOR:{

                }break;
                case DESCRIPTION:{

                }break;
                case COUNT:{

                }break;

            }
        }
        return answer;
    }
}
