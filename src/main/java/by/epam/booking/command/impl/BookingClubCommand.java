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
     * The Book logic.
     */
    protected BookLogic bookLogic;
    /**
     * The Reading plan logic.
     */
    protected ReadingPlanLogic readingPlanLogic;
    /**
     * The User logic.
     */
    protected UserLogic userLogic;

    /**
     * Instantiates a new Booking club command.
     */
    public BookingClubCommand(){
        bookLogic = BookLogic.getInstance();
        readingPlanLogic = ReadingPlanLogic.getInstance();
        userLogic = UserLogic.getInstance();
    }
}
