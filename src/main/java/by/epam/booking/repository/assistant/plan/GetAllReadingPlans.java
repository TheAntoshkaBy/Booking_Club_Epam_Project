package by.epam.booking.repository.assistant.plan;

import by.epam.booking.entity.Book;
import by.epam.booking.entity.ReadingPlan;
import by.epam.booking.repository.assistant.RepositoryHelper;
import by.epam.booking.repository.assistant.book.GetAllBooks;
import by.epam.booking.repository.impl.BookRepository;
import by.epam.booking.specification.Specification;
import by.epam.booking.specification.impl.book.SelectAllBooksForTable;
import by.epam.booking.specification.impl.plan.GetAllReadingPlansSpecification;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

public class GetAllReadingPlans extends RepositoryHelper {
    private static ArrayList<ReadingPlan> readingPlans;

    static {
        readingPlans = new ArrayList<ReadingPlan>();
    }

    public static ArrayList<ReadingPlan> getAllPlans(){
        Specification specification = new GetAllReadingPlansSpecification();
        ResultSet planSet = BookRepository.getINSTANCE().query(specification);
        ReadingPlan readingPlan;
        readingPlans.clear();
        try {
            while (planSet.next()){
                readingPlan = new ReadingPlan();
                readingPlan.setName(planSet.getString("r.name"));
                readingPlan.setDescription(planSet.getString("r.description"));
                readingPlan.setIdReadingPlan(planSet.getInt("r.idReadingPlan"));
                ArrayList<Book> books = GetAllBooks.getAllBooksInPlan(readingPlan.getIdReadingPlan());
                readingPlan.setBooks(books);
                readingPlans.add(readingPlan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                closeConnection(planSet.getStatement().getConnection());
                closeStatement(planSet.getStatement());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return readingPlans;
    }
}
