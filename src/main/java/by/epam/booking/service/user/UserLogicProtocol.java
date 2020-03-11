package by.epam.booking.service.user;

import by.epam.booking.entity.User;
import by.epam.booking.exception.ServiceException;

import java.util.ArrayList;

public interface UserLogicProtocol {

    User userGet(User transferredUser, UserInfoType... types) throws ServiceException;
    boolean userUpdate(User mutableUser, User changeParamOfUser, UserInfoType ... types) throws ServiceException;
    ArrayList<User> userGetAll(UserInfoType... types) throws ServiceException;
}
