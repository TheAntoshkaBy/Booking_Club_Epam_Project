package by.epam.booking.logic.book;

import by.epam.booking.entity.Book;
import by.epam.booking.logic.Logic;
import by.epam.booking.repository.impl.BookRepository;
import by.epam.booking.specification.impl.book.SelectAllBooksForTable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetAllBooksLogic extends Logic {
    private static ArrayList<Book> books;

    static {
        books = new ArrayList<Book>();
    }

    public static ArrayList<Book> getAllBooks(){
        ResultSet bookSet = BookRepository.getINSTANCE().query(new SelectAllBooksForTable());
        Book book;
        books.clear();
            try {
                while (bookSet.next()){
                    book = new Book();
                    book.setAuthor(bookSet.getString("author"));
                    book.setName(bookSet.getString("name"));
                    book.setCount(bookSet.getInt("count"));
                    book.setId(bookSet.getInt("idBook"));
                    book.setDescription(bookSet.getString("description"));
                    books.add(book);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        return books;
    }
    public static ArrayList<Book> getBooks() {
        return books;
    }
}
