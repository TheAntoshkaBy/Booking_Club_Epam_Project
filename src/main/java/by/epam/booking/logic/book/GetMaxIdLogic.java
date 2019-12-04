package by.epam.booking.logic.book;

import by.epam.booking.entity.Book;
import by.epam.booking.logic.Logic;
import by.epam.booking.repository.impl.BookRepository;
import by.epam.booking.specification.impl.book.SelectBookById;
import by.epam.booking.specification.impl.book.SelectMaxIdBook;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetMaxIdLogic extends Logic {
    public static Integer getMaxId(){
        Integer result = null;
        ResultSet resultBook = BookRepository.getINSTANCE().query(new SelectMaxIdBook());
        try {
            while (resultBook.next()){
                result = resultBook.getInt(1);
            }
            closeConnection(resultBook.getStatement().getConnection());
            closeStatement(resultBook.getStatement());
        }catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }
}
