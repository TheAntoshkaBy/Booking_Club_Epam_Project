package by.epam.booking.command.impl.guest.user;

import by.epam.booking.command.WebCommand;
import by.epam.booking.command.impl.BookingClubCommand;
import by.epam.booking.defence.EncryptPassword;
import by.epam.booking.exception.CommandException;
import by.epam.booking.exception.ServiceException;
import by.epam.booking.service.user.CheckUser;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.config.MessageManager;
import by.epam.booking.entity.User;
import by.epam.booking.command.Router;
import by.epam.booking.service.user.UserInfoType;
import by.epam.booking.service.user.impl.UserLogic;
import by.epam.booking.service.validation.LoginValidation;
import by.epam.booking.type.PageChangeType;
import by.epam.booking.type.ParameterName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;

public class LogInCommand extends BookingClubCommand implements WebCommand {

    public static final String UPLOAD_DIR = "profile_image";
    public static final String PATH_PAGE_USER = "path.page.user";
    public static final String PATH_PAGE_USER_PASSIVE = "path.page.user.passive";
    public static final String PATH_PAGE_LOGIN = "path.page.login";
    public static final String MESSAGE = "message.loginError";
    public static final String EMPTY_NAME = "--";
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_TYPE_PROFILE_PAGE_VALUE = "see";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router page = new Router();
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = EncryptPassword.encrypt(request.getParameter(PARAM_NAME_PASSWORD));
        HttpSession session = request.getSession();
        try {
            if (LoginValidation.getInstance().isXssAttack(login)
                    || LoginValidation.getInstance().isXssAttack(password)) {
                throw new CommandException();
            }

        if (CheckUser.isUserConsist(login, password)) {
            User user = new User();
            user.setLogin(login);
            user = userLogic.userGet(user, UserInfoType.ALL, UserInfoType.BOOK_NAME);
            if (CheckUser.userIsActive(user)) {
                request.getSession().setAttribute(ParameterName.PARAM_USER_LOGIN, user.getLogin());
                request.getSession().setAttribute(ParameterName.PARAM_USER_NAME, user.getName());
                request.getSession().setAttribute(ParameterName.PARAM_USER_SURNAME, user.getSurname());
                request.getSession().setAttribute(ParameterName.PARAM_USER_EMAIL, user.getEmail());
                request.getSession().setAttribute(ParameterName.PARAM_USER_ROLE, user.getRole().name());
                request.getSession().setAttribute(ParameterName.PARAM_USER_STATUS, user.getIsActive());
                String applicationDir = request.getServletContext().getRealPath("");
                request.getSession().setAttribute(ParameterName.PARAM_USER_IMAGE, UPLOAD_DIR +
                        File.separator + user.getImage());
                request.getSession().setAttribute(ParameterName.PARAM_LIST_OF_USER_COMPLETED_BOOKS,
                        user.getCompletedBooks());
                if (user.getBookName() != null) {
                    request.getSession().setAttribute(ParameterName.PARAM_USER_BOOK_NAME, user.getBookName());
                } else {
                    request.getSession().setAttribute(ParameterName.PARAM_USER_BOOK_NAME, EMPTY_NAME);
                }
                request.getSession().setAttribute(ParameterName.PARAM_FINANCE_MONEY, user.getMoneyBalance());
                if (user.getReadingPlanName() != null) {
                    request.getSession().setAttribute(ParameterName.PARAM_NAME_OF_READING_PLAN,
                            user.getReadingPlanName());
                } else {
                    request.getSession().setAttribute(ParameterName.PARAM_NAME_OF_READING_PLAN, EMPTY_NAME);
                }
                request.getSession().setAttribute(ParameterName.PARAM_TYPE_PROFILE, PARAM_TYPE_PROFILE_PAGE_VALUE);
                if (user.getBookId() != 0) {
                    request.getSession().setAttribute(ParameterName.PARAM_USER_BOOK_ID, user.getBookId());
                } else {
                    request.getSession().setAttribute(ParameterName.PARAM_USER_BOOK_ID, null);
                }
                if (user.getReadingPlanId() != 0) {
                    request.getSession().setAttribute(ParameterName.PARAM_USER_PLAN_ID, user.getReadingPlanId());
                } else {
                    request.getSession().setAttribute(ParameterName.PARAM_USER_PLAN_ID, null);
                }

                page.setPageFormat(PageChangeType.FORWARD);
                page.setPage(ConfigurationManager.getProperty(PATH_PAGE_USER));

            } else {
                request.getSession().setAttribute(ParameterName.PARAM_USER_LOGIN_INTERIM, user.getLogin());
                request.getSession().setAttribute(ParameterName.PARAM_USER_EMAIL_INTERIM, user.getEmail());
                page.setPageFormat(PageChangeType.REDIRECT);
                page.setPage(ConfigurationManager.getProperty(PATH_PAGE_USER_PASSIVE));
            }

        } else {
            page.setPage(ConfigurationManager.getProperty(PATH_PAGE_LOGIN));
            page.setPageFormat(PageChangeType.FORWARD);
            request.setAttribute(ParameterName.PARAM_LOGIN_ERROR, MessageManager.getProperty(MESSAGE));
            return page;
        }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }

}
