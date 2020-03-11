package by.epam.booking.command.impl.user.change;

import by.epam.booking.command.WebCommand;
import by.epam.booking.command.impl.BookingClubCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.config.MessageManager;
import by.epam.booking.entity.User;
import by.epam.booking.command.Router;
import by.epam.booking.exception.CommandException;
import by.epam.booking.exception.ServiceException;
import by.epam.booking.service.user.UserInfoType;
import by.epam.booking.service.user.impl.UserLogic;
import by.epam.booking.type.PageChangeType;
import by.epam.booking.type.ParameterName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Part;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class ChangeProfileImage extends BookingClubCommand implements WebCommand{

    public static final String UPLOAD_DIR = "profile_image";
    private static final String PATH_PAGE_USER = "path.page.user";
    private static final String MESSAGE = "message.changed.Save";
    private static final String PARAM_TYPE_VALUE = "change";
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException{
        Router page = new Router();
        User user = new User();
        String applicationDir = request.getServletContext().getRealPath("");
        String uploadFileDir = applicationDir + File.separator + UPLOAD_DIR + File.separator;
        File fileSaveDir = new File(uploadFileDir);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
         String imagePath = null;
        Part part = null;
        try {
            part = request.getParts().stream()
                    .filter(p -> p.getSubmittedFileName() != null && !p.getSubmittedFileName().isEmpty())
                    .findFirst().orElse(null);
        } catch (IOException | ServletException e) {
            logger.error("Load image problem ", e);
            throw new CommandException(e);
        }
        
        String path = part.getSubmittedFileName();
        imagePath = UUID.randomUUID() + path.substring(path.indexOf("."));
        try {
            part.write(uploadFileDir + imagePath);
        } catch (IOException e) {
            logger.error("Change profile image error!" + e);
            throw new CommandException(e);
        }

        user.setLogin((String) request.getSession().getAttribute(ParameterName.PARAM_USER_LOGIN));
        user.setImage(imagePath);
        try {
            userLogic.userUpdate(user,user, UserInfoType.UPDATE_PROFILE_IMAGE);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        request.getSession().setAttribute(ParameterName.PARAM_USER_IMAGE,UPLOAD_DIR + File.separator + imagePath);

        page.setPageFormat(PageChangeType.REDIRECT);
        page.setPage(ConfigurationManager.getProperty(PATH_PAGE_USER));
        request.getSession().setAttribute(ParameterName.PARAM_TYPE_PROFILE,PARAM_TYPE_VALUE);
        request.getSession().setAttribute(ParameterName.PARAM_USERNAME_ERROR,"");
        request.getSession().setAttribute(ParameterName.PARAM_CHANGE_SAVED,MessageManager.getProperty(MESSAGE));
        return page;
    }
}
