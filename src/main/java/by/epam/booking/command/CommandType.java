package by.epam.booking.command;

import by.epam.booking.command.impl.admin.book.*;
import by.epam.booking.command.impl.admin.finance.ToFinanceListCommand;
import by.epam.booking.command.impl.guest.book.*;
import by.epam.booking.command.impl.guest.plan.ToConcreteReadingPlanCommand;
import by.epam.booking.command.impl.guest.plan.ToReadingPlansCommand;
import by.epam.booking.command.impl.guest.user.*;
import by.epam.booking.command.impl.user.*;
import by.epam.booking.command.impl.user.change.*;

public enum CommandType {

    TO_LOGIN {
        {
            this.command = new ToLogInCommand();
        }
    },
    TO_REGISTRATION{
        {
            this.command = new ToRegistrationCommand();
        }
    },
    LOGIN{
        {
            this.command = new LogInCommand();
        }
    },
    REGISTRATION{
        {
            this.command = new RegistrationCommand();
        }
    },
    LOCALE{
        {
            this.command = new LocaleCommand();
        }
    },
    LOGOUT{
        {
            this.command = new LogOutCommand();
        }
    },
    TO_LIBRARY{
        {
            this.command = new ToLibraryCommand();
        }
    },
    TO_MAIN{
        {
            this.command = new ToMainPageCommand();
        }
    },
    SETTINGS{
        {
            this.command = new SettingsProfileCommand();
        }
    },
    CHANGE_PROFILE_NAME{
        {
            this.command = new ChangeProfileName();
        }
    },
    CHANGE_PROFILE_SURNAME{
        {
            this.command = new ChangeProfileSurname();
        }
    },
    CHANGE_PROFILE_LOGIN{
        {
            this.command = new ChangeProfileLogin();
        }
    },
    TO_BOOK{
        {
            this.command = new ToBookCommand();
        }
    },
    NEXT_BOOK{
        {
            this.command = new NextBookCommand();
        }
    },
    PREVIOUS_BOOK{
        {
            this.command = new PreviousBookCommand();
        }
    },
    CHANGE_PROFILE_IMAGE{
        {
            this.command = new ChangeProfileImage();
        }
    },
    DELETE_USER_BOOK{
        {
            this.command = new DeleteUserBookCommand();
        }
    },
    ADD_USER_BOOK{
        {
            this.command = new AddNewBookCommand();
        }
    },
    ADD_BOOK_COMMENT{
        {
            this.command = new NewCommentCommand();
        }
    },
    TO_READING_PLANS{
        {
            this.command = new ToReadingPlansCommand();
        }
    },
    TO_READING_PLAN{
        {
            this.command = new ToConcreteReadingPlanCommand();

        }
    },
    DELETE_READING_PLAN{
        {
            this.command = new DeleteReadingPlan();
        }
    },
    TO_FRONT_PAGE{
        {
            this.command = new ToFrontPageCommand();
        }
    },
    TO_BOOK_SETTINGS{
        {
            this.command = new ToBookSettingsCommand();
        }
    },
    CHANGE_BOOK_NAME{
        {
            this.command = new ChangeBookNameCommand();
        }
    },
    CHANGE_BOOK_AUTHOR{
        {
            this.command = new ChangeBookAuthorCommand();
        }
    },
    CHANGE_BOOK_DESCRIPTION{
        {
            this.command = new ChangeBookDescriptionCommand();
        }
    },
    GO_TO_ADD_BOOK{
        {
            this.command = new GoToAddBookCommand();
        }
    },
    ADD_BOOK{
        {
            this.command = new AddBookCommand();
        }
    },
    SEND_TO_ADMIN{
        {
            this.command = new SendToAdminCommand();
        }
    },
    TO_LIST_COMPLETE_BOOKS{
        {
            this.command = new ToListCompleteBooks();
        }
    },
    NEW_COMPLETED_BOOK{
        {
            this.command = new NewCompletedBook();
        }
    },
    DELETE_COMPLETED_BOOK{
        {
            this.command = new DeleteCompletedBook();
        }
    },
    DELETE_BOOK{
        {
            this.command = new DeleteBookCommand();
        }
    },
    CHANGE_BOOK_IMAGE{
        {
            this.command = new ChangeBookImageCommand();
        }
    },
    PAY{
        {
            this.command = new PayCommand();
        }
    },
    TO_FINANCE_LIST{
        {
            this.command = new ToFinanceListCommand();
        }
    },
    ERROR_STEP{
        {
            this.command = new ErrorStepCommand();
        }
    }
    ;

    WebCommand command;
    public WebCommand getCurrentCommand() {
        return command;
    }
}
