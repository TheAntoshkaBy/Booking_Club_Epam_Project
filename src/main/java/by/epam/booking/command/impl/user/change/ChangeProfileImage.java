package by.epam.booking.command.impl.user.change;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.config.MessageManager;
import by.epam.booking.entity.User;
import by.epam.booking.format.PageFormat;
import by.epam.booking.service.user.UserInfoType;
import by.epam.booking.service.user.UserLogic;

import javax.servlet.ServletException;
import javax.servlet.http.Part;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class ChangeProfileImage implements WebCommand{

    public static final String UPLOAD_DIR = "profile_image";

    @Override
    public PageFormat execute(HttpServletRequest request) {
        PageFormat page = new PageFormat();
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

        }
        
        String path = part.getSubmittedFileName();
        imagePath = UUID.randomUUID() + path.substring(path.indexOf("."));
        try {
            part.write(uploadFileDir + imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        user.setLogin((String) request.getSession().getAttribute("login"));
        user.setImage(imagePath);
        UserLogic.userUpdate(user,user, UserInfoType.UPDATE_PROFILE_IMAGE);
        request.getSession().setAttribute("userImage",UPLOAD_DIR + File.separator + imagePath);

        page.setPage(ConfigurationManager.getProperty("path.page.user"));
        request.setAttribute("type","change");
        request.getSession().setAttribute("usernameError","");
        request.getSession().setAttribute("ChangedSave",MessageManager.getProperty("message.changed.Save"));
        return page;
    }
}
