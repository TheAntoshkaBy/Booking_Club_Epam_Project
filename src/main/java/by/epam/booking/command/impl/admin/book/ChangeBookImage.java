package by.epam.booking.command.impl.admin.book;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.config.MessageManager;
import by.epam.booking.entity.Book;
import by.epam.booking.entity.User;
import by.epam.booking.format.PageFormat;
import by.epam.booking.service.book.BookInfoType;
import by.epam.booking.service.book.BookLogic;
import by.epam.booking.service.user.UserInfoType;
import by.epam.booking.service.user.UserLogic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class ChangeBookImage implements WebCommand {

    public static final String UPLOAD_DIR = "book_image";

    @Override
    public PageFormat execute(HttpServletRequest request) {
        PageFormat page = new PageFormat();
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

        book.setId(Integer.parseInt(request.getParameter("bookId")));
        book.setImage(imagePath);
        BookLogic.bookUpdate(book,book, BookInfoType.IMAGE);
        request.getSession().setAttribute("userImage",UPLOAD_DIR + File.separator + imagePath);

        page.setPage(ConfigurationManager.getProperty("path.page.user"));
        request.setAttribute("type","change");
        request.getSession().setAttribute("usernameError","");
        request.getSession().setAttribute("ChangedSave", MessageManager.getProperty("message.changed.Save"));
        return page;
    }
}
