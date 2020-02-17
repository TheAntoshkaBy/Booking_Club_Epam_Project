package by.epam.booking.service.user;

import by.epam.booking.command.validator.UserValidator;
import by.epam.booking.entity.User;
import by.epam.booking.repository.assistant.user.*;
import by.epam.booking.repository.assistant.user.changeLogic.*;
import by.epam.booking.specification.impl.money.UpdateMoneySpecification;
import by.epam.booking.specification.impl.user.update.*;


public class UserLogic {
    public static User userGet(User transferredUser, UserInfoType... types){
        User user = new User();
        for (UserInfoType type : types) {
            switch (type){
                case ALL:{
                    user = UserInfoByLogin.searchUserByLogin(transferredUser.getLogin());
                    assert user != null;
                    user.setCompletedBooks(UserInfoByLogin.getCompletedBooks(transferredUser.getLogin()));
                    user.setImage(GetUserDataString.getString(transferredUser.getLogin()));
                }break;
                case NAME:{
                    assert user != null;
                    user.setName(transferredUser.getLogin());
                }break;

                case BOOK_NAME:{
                    user.setBookName(GetBookName.getBookName(transferredUser.getLogin()));
                }break;
                case READING_PLAN_NAME:{

                }break;
                case BOOK_COMPLETED:{

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
                    answer=true;
                }break;
                case SURNAME:{
                    ChangeSurname.changeSurname(new UpdateSurnameByLogin(
                            mutableUser.getLogin(),
                            changeParamOfUser.getSurname()));
                    answer=true;
                }break;
                case LOGIN:{
                    if(!UserValidator.changeLogin(changeParamOfUser.getLogin())){
                        return false;
                    }
                   answer = Login.changeLogIn(new UpdateLoginByLogin(mutableUser.getLogin(),changeParamOfUser.getLogin()));
                }break;
                case BOOK:{
                    answer = ChangeBookId.changeBookId(new UpdateBookId(mutableUser.getLogin(), changeParamOfUser.getBookId()));
                }break;
                case READING_PLAN_NAME:{
                }break;
                case DELETE_READING_PLAN:{
                    answer = ChangeReadingPlanId.changeReadingPlanId(new UpdateReadingPlanSpecification(mutableUser.getLogin()));
                }break;
                case ADD_NEW_BOOK_COMPLETED:{
                    answer = ChangeUser.change(new UpdateBookCompletedList(mutableUser.getLogin(),mutableUser.getBookId()));
                }break;
                case DELETE_BOOK_COMPLETED:{
                    answer = ChangeUser.change(new DeleteBookFromCompletedList(mutableUser.getLogin(),mutableUser.getBookId()));
                }break;
                case UPDATE_PROFILE_IMAGE:{
                    answer = ChangeUser.change(new UpdateProfileImage(mutableUser.getLogin(),mutableUser.getImage()));
                }break;
                case MONEY_BALANCE:{
                    answer = TransactionFromMoneyBalance.moneyExecutor(mutableUser.getMoneyBalance(),mutableUser.getLogin(),mutableUser.getBuffMoneyType(),mutableUser.getBuffDate());
                }break;
            }
        }
        return answer;
    }
    public static boolean registration(User user){
        return Registration.registration(user.getLogin(),user.getPassword(),user.getName(),user.getSurname(),user.getEmail());
    }
}
