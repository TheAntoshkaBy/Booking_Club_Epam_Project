package by.epam.booking.service.user;

import by.epam.booking.defence.EncryptPassword;
import by.epam.booking.entity.User;
import by.epam.booking.exception.RepositoryException;
import by.epam.booking.repository.assistant.user.*;
import by.epam.booking.repository.assistant.user.change.*;
import by.epam.booking.service.validation.NewUserValidator;
import by.epam.booking.specification.impl.user.update.*;
import by.epam.booking.type.ParameterName;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class UserLogic {
    public static User userGet(User transferredUser, UserInfoType... types) throws RepositoryException {
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
    public static boolean userUpdate(User mutableUser, User changeParamOfUser, UserInfoType ... types) throws RepositoryException {
        boolean answer  = false;
        for (UserInfoType type : types) {
            switch (type){
                case ALL:{

                }break;
                case NAME:{
                    ChangeUserInfo.change(
                            new UpdateUsernameByLoginSpecification(mutableUser.getLogin(), changeParamOfUser.getName()));
                    answer=true;
                }break;
                case SURNAME:{
                    ChangeUserInfo.change(new UpdateSurnameByLoginSpecification(
                            mutableUser.getLogin(),
                            changeParamOfUser.getSurname()));
                    answer=true;
                }break;
                case LOGIN:{
                    if(!CheckUser.changeLogin(changeParamOfUser.getLogin())){
                        return false;
                    }
                   answer = Login.changeLogIn(new UpdateLoginByLoginSpecification(mutableUser.getLogin(),changeParamOfUser.getLogin()));
                }break;
                case BOOK:{
                    answer = ChangeUserInfo.change(new UpdateBookIdSpecification(mutableUser.getLogin(), changeParamOfUser.getBookId()));
                }break;
                case READING_PLAN_NAME:{
                }break;
                case DELETE_READING_PLAN:{
                    answer = ChangeUserInfo.change(new UpdateReadingPlanSpecification(mutableUser.getLogin()));
                }break;
                case ADD_NEW_BOOK_COMPLETED:{
                    answer = ChangeUserInfo.change(new UpdateBookCompletedListSpecification(mutableUser.getLogin(),mutableUser.getBookId()));
                }break;
                case DELETE_BOOK_COMPLETED:{
                    answer = ChangeUserInfo.change(new DeleteBookFromCompletedListSpecification(mutableUser.getLogin(),mutableUser.getBookId()));
                }break;
                case UPDATE_PROFILE_IMAGE:{
                    answer = ChangeUserInfo.change(new UpdateProfileImageSpecification(mutableUser.getLogin(),mutableUser.getImage()));
                }break;
                case MONEY_BALANCE:{
                    answer = TransactionFromMoneyBalance.moneyExecutor(mutableUser.getMoneyBalance(),mutableUser.getLogin(),mutableUser.getBuffMoneyType(),mutableUser.getBuffDate());
                }break;
            }
        }
        return answer;
    }

    private static Map<String, Boolean> checkUserDataIsCorrect(String name, String surname, String login, String password, String email) {
        boolean incorrectName = !NewUserValidator.getInstance().isCorrectName(name);
        boolean incorrectSurname = !NewUserValidator.getInstance().isCorrectSurName(surname);
        boolean incorrectLogin = !NewUserValidator.getInstance().isCorrectLogin(login);
        boolean incorrectPass = !NewUserValidator.getInstance().isCorrectPassword(password);
        boolean incorrectEmail = !NewUserValidator.getInstance().isCorrectEmail(email);

        Map<String, Boolean> map = new HashMap<>();
        map.put(ParameterName.PARAM_USERNAME_ERROR, incorrectName);
        map.put(ParameterName.PARAM_SURNAME_ERROR, incorrectSurname);
        map.put(ParameterName.PARAM_LOGIN_ERROR, incorrectLogin);
        map.put(ParameterName.PARAM_PASS_ERROR, incorrectPass);
        map.put(ParameterName.PARAM_EMAIL_ERROR, incorrectEmail);
        return map;
    }

    public static Map<String,Boolean> registration(User user) throws SQLException, RepositoryException {

        Map<String, Boolean> userDataCheck =
                checkUserDataIsCorrect(
                        user.getName(),
                        user.getSurname(),
                        user.getLogin(),
                        user.getPassword(),
                        user.getEmail()
                );
        boolean isValid = userDataCheck.values().stream().filter(o -> o.equals(true)).findAny().orElse(false);
        if(!isValid){
            Registration.registration(
                    user.getLogin(),
                    EncryptPassword.encrypt(user.getPassword()),
                    user.getName(),
                    user.getSurname(),
                    user.getEmail());
            userDataCheck.put("correct",true);
        }else {
            userDataCheck.put("correct",false);
        }
        return userDataCheck;

    }
}
