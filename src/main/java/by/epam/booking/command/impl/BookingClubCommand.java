package by.epam.booking.command.impl;

import by.epam.booking.command.WebCommand;
import by.epam.booking.service.book.impl.BookLogic;
import by.epam.booking.service.plan.impl.ReadingPlanLogic;
import by.epam.booking.service.user.impl.UserLogic;

public abstract class BookingClubCommand implements WebCommand {

    protected BookLogic bookLogic;
    protected ReadingPlanLogic readingPlanLogic;
    protected UserLogic userLogic;

    public BookingClubCommand(){
        bookLogic = BookLogic.getInstance();
        readingPlanLogic = ReadingPlanLogic.getInstance();
        userLogic = UserLogic.getInstance();
    }
}
