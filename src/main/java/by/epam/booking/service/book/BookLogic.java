package by.epam.booking.service.book;

import by.epam.booking.command.validator.UserValidator;
import by.epam.booking.entity.Book;
import by.epam.booking.entity.User;
import by.epam.booking.repository.assistant.book.GetBookInfo;
import by.epam.booking.repository.assistant.book.GetMaxId;
import by.epam.booking.repository.assistant.user.Login;
import by.epam.booking.repository.assistant.user.UserInfoByLogin;
import by.epam.booking.repository.assistant.user.changeLogic.ChangeSurname;
import by.epam.booking.repository.assistant.user.changeLogic.ChangeUsername;
import by.epam.booking.service.user.UserInfoType;
import by.epam.booking.specification.impl.user.update.UpdateLoginByLogin;
import by.epam.booking.specification.impl.user.update.UpdateSurnameByLogin;
import by.epam.booking.specification.impl.user.update.UpdateUsernameByLogin;

public class BookLogic {
    public static Book bookGet(Book transferredBook, BookInfoType... types){
        Book book = new Book();
        for (BookInfoType type : types) {
            switch (type){
                case ALL:{
                    if(GetBookInfo.getBookById(transferredBook)){
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
