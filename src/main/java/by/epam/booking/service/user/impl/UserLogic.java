package by.epam.booking.service.user.impl;

import by.epam.booking.defence.EncryptPassword;
import by.epam.booking.entity.User;
import by.epam.booking.exception.RepositoryException;
import by.epam.booking.exception.ServiceException;
import by.epam.booking.repository.assistant.user.*;
import by.epam.booking.repository.assistant.user.change.*;
import by.epam.booking.service.user.CheckUser;
import by.epam.booking.service.user.UserInfoType;
import by.epam.booking.service.user.UserLogicProtocol;
import by.epam.booking.specification.impl.user.update.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;


public class UserLogic implements UserLogicProtocol {

    private static UserLogic INSTANCE;
    private static AtomicBoolean instanceCreated = new AtomicBoolean();
    private static final ReentrantLock getInstanceLock = new ReentrantLock();
    private String CORRECT_USER_DATA_PARAMETER  = "correct";
    private static Logger logger = LogManager.getLogger();

    private UserLogic(){
    }

    public static UserLogic getInstance() {
        if (!instanceCreated.get()) {
            getInstanceLock.lock();
            try {
                if (null == INSTANCE) {
                    INSTANCE = new UserLogic();
                    instanceCreated.set(true);
                }
            } finally {
                getInstanceLock.unlock();
            }
        }
        return INSTANCE;
    }


    @Override
    public User userGet(User transferredUser, UserInfoType... types) throws ServiceException {
        User user = new User();
        for (UserInfoType type : types) {
            switch (type){
                case ALL:{
                    try {
                        user = UserInfoByLogin.searchUserByLogin(transferredUser.getLogin());
                        user.setCompletedBooks(UserInfoByLogin.getCompletedBooks(transferredUser.getLogin()));
                        user.setImage(GetUserDataString.getString(transferredUser.getLogin()));
                    }catch (RepositoryException e){
                        logger.error(e);
                        throw new ServiceException(e);
                    }
                }break;
                case NAME:{
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

    @Override
    public boolean userUpdate(User mutableUser, User changeParamOfUser, UserInfoType ... types) throws ServiceException {
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

    @Override
    public ArrayList<User> userGetAll(UserInfoType... types) throws ServiceException {
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

    public Map<String,Boolean> registration(User user) throws ServiceException {

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
            userDataCheck.put(CORRECT_USER_DATA_PARAMETER,true);
        }else {
            userDataCheck.put(CORRECT_USER_DATA_PARAMETER,false);
        }
        return userDataCheck;

    }
}
