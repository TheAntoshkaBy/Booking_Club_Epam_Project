package by.epam.booking.repository.assistant.book;

import by.epam.booking.entity.Book;
import by.epam.booking.repository.assistant.RepositoryHelper;
import by.epam.booking.repository.impl.BookRepository;
import by.epam.booking.specification.impl.book.SelectBookById;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetBookInfo extends RepositoryHelper {
    public static boolean getBookById(Book book){
        ResultSet resultBook = BookRepository.getInstance().query(new SelectBookById(book.getId()));
        try {
            while (resultBook.next()){
                book.setId(resultBook.getInt("idBook"));
                book.setCount(resultBook.getInt("count"));
                book.setAuthor(resultBook.getString("author"));
                book.setName(resultBook.getString("name"));
                book.setDescription(resultBook.getString("description"));
            }
            // FIXME: 03.12.2019 Фигурные скобки
            if(book.getName()==null){
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                closeConnection(resultBook.getStatement().getConnection());
                closeStatement(resultBook.getStatement());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}