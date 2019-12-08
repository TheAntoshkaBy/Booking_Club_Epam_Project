package by.epam.booking.service.user;

import by.epam.booking.command.validator.UserValidator;
import by.epam.booking.entity.User;
import by.epam.booking.repository.assistant.user.Login;
import by.epam.booking.repository.assistant.user.Registration;
import by.epam.booking.repository.assistant.user.UserInfoByLogin;
import by.epam.booking.repository.assistant.user.changeLogic.ChangeSurname;
import by.epam.booking.repository.assistant.user.changeLogic.ChangeUsername;
import by.epam.booking.specification.impl.user.update.UpdateLoginByLogin;
import by.epam.booking.specification.impl.user.update.UpdateSurnameByLogin;
import by.epam.booking.specification.impl.user.update.UpdateUsernameByLogin;


public class UserLogic {
    public static User userGet(User transferredUser, UserInfoType... types){
        User user = new User();
        for (UserInfoType type : types) {
            switch (type){
                case ALL:{
                    user =  UserInfoByLogin.searchUserByLogin(transferredUser.getLogin());
                }break;
                case NAME:{
                    assert user != null;
                    user.setName(transferredUser.getLogin());
                }break;
                case SURNAME:{

                }break;
                case EMAIL:{

                }break;
                case PASSWORD:{

                }break;
                case LOGIN:{

                }break;
                case ROLE:{

                }break;

                case IS_ACTIVE:{

                }break;
                case MONEY_BALANCE:{

                }break;
                case BOOK_NAME:{

                }break;
                case READING_PLAN_NAME:{

                }break;
            }
        }
        return user;
    }
    public static boolean userUpdate(User mutableUser, User changeParamOfUser, UserInfoType ... types){
        boolean answer  = false;
        for (UserInfoType type : types) {
            switch (type){
                case ALL:{

                }break;
                case NAME:{
                    ChangeUsername.changeUsername(
                            new UpdateUsernameByLogin(mutableUser.getLogin(), changeParamOfUser.getName()));
                }break;
                case SURNAME:{
                    ChangeSurname.changeSurname(new UpdateSurnameByLogin(
                            mutableUser.getLogin(),
                            changeParamOfUser.getSurname()));
                }break;
                case EMAIL:{

                }break;
                case PASSWORD:{

                }break;
                case LOGIN:{
                    if(!UserValidator.changeLogin(changeParamOfUser.getLogin())){
                        return false;
                    }
                   answer = Login.changeLogIn(new UpdateLoginByLogin(mutableUser.getLogin(),changeParamOfUser.getLogin()));
                }break;
                case ROLE:{

                }break;

                case IS_ACTIVE:{

                }break;
                case MONEY_BALANCE:{

                }break;
                case BOOK_NAME:{

                }break;
                case READING_PLAN_NAME:{

                }break;
            }
        }
        return answer;
    }
    public static boolean registration(User user){
        return Registration.registration(user.getLogin(),user.getPassword(),user.getName(),user.getSurname(),user.getEmail());
    }
}
