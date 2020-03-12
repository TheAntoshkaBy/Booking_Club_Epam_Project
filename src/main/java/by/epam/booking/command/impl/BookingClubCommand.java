package by.epam.booking.command.impl;

import by.epam.booking.command.WebCommand;
import by.epam.booking.service.book.impl.BookLogic;
import by.epam.booking.service.plan.impl.ReadingPlanLogic;
import by.epam.booking.service.user.impl.UserLogic;

/**
 * The type Booking club command.
 */
public abstract class BookingClubCommand implements WebCommand {

    /**
     * Responsible for the book management logic in the application.
     */
    protected BookLogic bookLogic;
    /**
     * Responsible for the Reading plans management logic in the application.
     */
    protected ReadingPlanLogic readingPlanLogic;
    /**
     * Responsible for the user management logic in the application.
     */
    protected UserLogic userLogic;

    /**
     * Instantiates a logic classes for Application commands.
     */
    public BookingClubCommand(){
        bookLogic = BookLogic.getInstance();
        readingPlanLogic = ReadingPlanLogic.getInstance();
        userLogic = UserLogic.getInstance();
    }
}
