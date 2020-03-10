package by.epam.booking.service.user;

import by.epam.booking.defence.EncryptPassword;
import by.epam.booking.entity.User;
import by.epam.booking.exception.RepositoryException;
import by.epam.booking.exception.ServiceException;
import by.epam.booking.repository.assistant.book.change.ChangeBook;
import by.epam.booking.repository.assistant.user.*;
import by.epam.booking.repository.assistant.user.change.*;
import by.epam.booking.service.validation.NewUserValidator;
import by.epam.booking.specification.impl.user.update.*;
import by.epam.booking.type.ParameterName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class UserLogic {

    private static Logger logger = LogManager.getLogger();

    public static User userGet(User transferredUser, UserInfoType... types) throws ServiceException {
        User user = new User();
        for (UserInfoType type : types) {
            switch (type){
                case ALL:{
                    try {
                        user = UserInfoByLogin.searchUserByLogin(transferredUser.getLogin());
                        assert user != null;
                        user.setCompletedBooks(UserInfoByLogin.getCompletedBooks(transferredUser.getLogin()));
                        user.setImage(GetUserDataString.getString(transferredUser.getLogin()));
                    }catch (RepositoryException e){
                        logger.error(e);
                        throw new ServiceException(e);
                    }
                }break;
                case NAME:{
                    assert user != null;
                    user.setName(transferredUser.getLogin());
                }break;

                case BOOK_NAME:{
                    try {
                        user.setBookName(GetBookName.getBookName(transferredUser.getLogin()));
                    }catch (RepositoryException e){
                        logger.error(e);
                        throw new ServiceException(e);
                    }
                }break;
            }
        }
        return user;
    }
    public static boolean userUpdate(User mutableUser, User changeParamOfUser, UserInfoType ... types) throws ServiceException {
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
                    try {
                        answer = Login.changeLogIn(new UpdateLoginByLoginSpecification(mutableUser.getLogin(),
                                changeParamOfUser.getLogin()));
                    } catch (RepositoryException e) {
                        logger.error(e);
                        throw new ServiceException(e);
                    }
                }break;
                case BOOK:{
                    answer = ChangeUserInfo.change(new UpdateBookIdSpecification(mutableUser.getLogin(),
                            changeParamOfUser.getBookId()));
                }break;
                case READING_PLAN_NAME:{
                }break;
                case DELETE_READING_PLAN:{
                    answer = ChangeUserInfo.change(new UpdateReadingPlanSpecification(mutableUser.getLogin()));
                }break;
                case ADD_NEW_BOOK_COMPLETED:{
                    answer = ChangeUserInfo.change(new UpdateBookCompletedListSpecification(mutableUser.getLogin(),
                            mutableUser.getBookId()));
                }break;
                case DELETE_BOOK_COMPLETED:{
                    answer = ChangeUserInfo.change(new DeleteBookFromCompletedListSpecification(mutableUser.getLogin(),
                            mutableUser.getBookId()));
                }break;
                case UPDATE_PROFILE_IMAGE:{
                    answer = ChangeUserInfo.change(new UpdateProfileImageSpecification(mutableUser.getLogin(),
                            mutableUser.getImage()));
                }break;
                case MONEY_BALANCE:{
                    try {
                        answer = TransactionFromMoneyBalance.moneyExecutor(mutableUser.getMoneyBalance(),
                                mutableUser.getLogin(),mutableUser.getBuffMoneyType(),mutableUser.getBuffDate());
                    } catch (RepositoryException | SQLException e) {
                        logger.error(e);
                        throw new ServiceException(e);
                    }
                }break;
                case IS_ACTIVE:{
                    answer = ChangeUserInfo.change(new UpdateUserStatusSpecification(mutableUser.getLogin(),mutableUser.getIsActive()));
                }break;
                case READING_PLAN:{
                    answer = ChangeUserInfo.change(new UpdateUserReadingPlanSpecification(mutableUser.getLogin(),
                            mutableUser.getReadingPlanId()));
                }break;
            }
        }
        return answer;
    }

    public static ArrayList<User> userGetAll(UserInfoType... types) throws ServiceException {
        ArrayList<User> users = null;
        for (UserInfoType type : types){
            switch (type){
                case GET_ALL_USERS:{
                    try {
                        ArrayList<String> usersLogin = GetAllUsersId.getAllBooksId();

                        ArrayList<User> buffUser = new ArrayList<>();
                        usersLogin.forEach(login -> {
                            try {
                                buffUser.add(userGet(new User(login), UserInfoType.ALL));
                            } catch (ServiceException e) {
                                logger.error("Users get error!", e);
                            }
                        });
                        users = buffUser;
                    } catch (RepositoryException e) {
                        logger.error(e);
                        throw new ServiceException(e);
                    }
                }break;
            }
        }

        return users;
    }

    public static Map<String,Boolean> registration(User user) throws ServiceException {

        Map<String, Boolean> userDataCheck =
                CheckUser.checkUserDataIsCorrect(
                        user.getName(),
                        user.getSurname(),
                        user.getLogin(),
                        user.getPassword(),
                        user.getEmail()
                );

        boolean isValid = userDataCheck.values().stream().filter(o -> o.equals(true)).findAny().orElse(false);
        if(!isValid){
            try {
                Registration.registration(
                        user.getLogin(),
                        EncryptPassword.encrypt(user.getPassword()),
                        user.getName(),
                        user.getSurname(),
                        user.getEmail());
            } catch (RepositoryException e) {
                logger.error(e);
                throw new ServiceException(e);
            }
            userDataCheck.put("correct",true);
        }else {
            userDataCheck.put("correct",false);
        }
        return userDataCheck;

    }
}
