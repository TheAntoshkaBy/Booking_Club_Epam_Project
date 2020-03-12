package by.epam.booking.service.user;

import by.epam.booking.entity.User;
import by.epam.booking.exception.ServiceException;

import java.util.ArrayList;

/**
 * The interface User logic protocol.
 */
public interface UserLogicProtocol {

    /**
     * User get user.
     *
     * @param transferredUser the transferred user
     * @param types           the types
     * @return the user
     * @throws ServiceException the service exception
     */
    User userGet(User transferredUser, UserInfoType... types) throws ServiceException;

    /**
     * User update boolean.
     *
     * @param mutableUser       the mutable user
     * @param changeParamOfUser the change param of user
     * @param types             the types
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean userUpdate(User mutableUser, User changeParamOfUser, UserInfoType ... types) throws ServiceException;

    /**
     * User get all array list.
     *
     * @param types the types
     * @return the array list
     * @throws ServiceException the service exception
     */
    ArrayList<User> userGetAll(UserInfoType... types) throws ServiceException;
}
