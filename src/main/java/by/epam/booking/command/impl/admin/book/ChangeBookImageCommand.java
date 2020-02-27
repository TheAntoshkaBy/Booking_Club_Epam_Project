package by.epam.booking.command.impl.admin.book;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.config.MessageManager;
import by.epam.booking.entity.Book;
import by.epam.booking.command.Router;
import by.epam.booking.service.book.BookInfoType;
import by.epam.booking.service.book.BookLogic;
import by.epam.booking.type.ParameterName;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class ChangeBookImageCommand implements WebCommand {

    public static final String UPLOAD_DIR = "book_image";
    public static final String PATH = "path.page.user";
    public static final String PROFILE_TYPE = "change";
    public static final String SAVING_MESSAGE = "message.changed.Save";


    @Override
    public Router execute(HttpServletRequest request) {
        Router page = new Router();
        Book book = new Book();
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

        book.setId(Integer.parseInt(request.getParameter(ParameterName.PARAM_BOOK_ID)));
        book.setImage(imagePath);
        BookLogic.bookUpdate(book, book, BookInfoType.IMAGE);
        request.getSession().setAttribute(ParameterName.PARAM_BOOK_IMAGE, UPLOAD_DIR + File.separator + imagePath);

        page.setPage(ConfigurationManager.getProperty(PATH));
        request.setAttribute(ParameterName.PARAM_TYPE_PROFILE, PROFILE_TYPE);
        request.getSession().setAttribute(ParameterName.PARAM_USERNAME_ERROR, "");
        request.getSession().setAttribute(ParameterName.PARAM_CHANGED_SAVE, MessageManager.getProperty(SAVING_MESSAGE));
        return page;
    }
}
