package by.epam.booking.command.impl.guest.book;

import by.epam.booking.command.WebCommand;
import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.entity.Book;
import by.epam.booking.format.PageFormat;
import by.epam.booking.repository.assistant.book.GetAllBooks;
import by.epam.booking.repository.assistant.user.UserInfoByLogin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class ToLibraryCommand implements WebCommand {

    @Override
    public PageFormat execute(HttpServletRequest request) {
        PageFormat page = new PageFormat();
        HttpSession session = request.getSession();
        ArrayList<Book> books =  GetAllBooks.getAllBooks();

        String login = UserInfoByLogin.getUserName((String) session.getAttribute("login"));
        if(login == null){
            request.setAttribute("user", "Guest");
        }else {
            request.setAttribute("user", login);
            ArrayList<Integer> booksId = (ArrayList<Integer>) request.getSession().getAttribute("completedBooksId");
            if(booksId != null){
            for(int i = 0; i<books.size();i++){
                if(booksId.contains(books.get(i).getId())){
                    books.get(i).setStatus(true);
                }
            }

            }
        }
        request.setAttribute("books", books);
        page.setPage( ConfigurationManager.getProperty("path.page.library"));

        return page;
    }
}
