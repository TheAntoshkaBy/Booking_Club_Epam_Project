package by.epam.booking.logic.book;

import by.epam.booking.entity.Book;
import by.epam.booking.logic.Logic;
import by.epam.booking.repository.impl.BookRepository;
import by.epam.booking.specification.Specification;
import by.epam.booking.specification.impl.book.SelectBookById;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetBookInfoLogic extends Logic {
    public static boolean getBookById(Book book){
        ResultSet resultBook = BookRepository.getINSTANCE().query(new SelectBookById(book.getId()));
        try {
            while (resultBook.next()){
                book.setId(resultBook.getInt("idBook"));
                book.setCount(resultBook.getInt("count"));
                book.setAuthor(resultBook.getString("author"));
                book.setName(resultBook.getString("name"));
                book.setDescription(resultBook.getString("description"));
            }
            closeConnection(resultBook.getStatement().getConnection());
            closeStatement(resultBook.getStatement());
            // FIXME: 03.12.2019 Фигурные скобки
            if(book.getName()==null)
                return false;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
}