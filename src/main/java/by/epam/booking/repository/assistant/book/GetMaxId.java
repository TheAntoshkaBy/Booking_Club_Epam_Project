package by.epam.booking.repository.assistant.book;

import by.epam.booking.repository.assistant.RepositoryHelper;
import by.epam.booking.repository.impl.BookRepository;
import by.epam.booking.specification.impl.book.SelectMaxIdBook;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetMaxId extends RepositoryHelper {
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
