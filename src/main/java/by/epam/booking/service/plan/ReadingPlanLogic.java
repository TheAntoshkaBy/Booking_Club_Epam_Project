package by.epam.booking.service.plan;

import by.epam.booking.entity.Book;
import by.epam.booking.entity.ReadingPlan;
import by.epam.booking.exception.RepositoryException;
import by.epam.booking.repository.assistant.book.*;
import by.epam.booking.service.book.BookInfoType;
import by.epam.booking.specification.impl.book.AddNewBookCommentSpecification;

import java.sql.SQLException;

public class ReadingPlanLogic {
    public static ReadingPlan planGet(ReadingPlan transferredPlan, ReadingPlanInfoType... types) throws SQLException, RepositoryException {

        for (ReadingPlanInfoType type : types) {
            switch (type) {
                case GET_ALL_READING_PLANS:{

                }break;
                case GET_ALL_BOOKS_OF_PLAN:{
                    transferredPlan.setBooks(GetAllBooks.getAllBooksInPlan(transferredPlan.getIdReadingPlan()));
                }break;
            }
        }
        return transferredPlan;
    }

    public static boolean bookUpdate(Book mutableBook, Book changeParamOfBook, BookInfoType... types) {
        boolean answer = false;
        for (BookInfoType type : types) {
            switch (type) {

            }
        }
        return answer;
    }
}
