package by.epam.booking.command;

import by.epam.booking.command.impl.guest.book.NextBookCommand;
import by.epam.booking.command.impl.guest.book.PreviousBookCommand;
import by.epam.booking.command.impl.guest.book.ToBookCommand;
import by.epam.booking.command.impl.guest.book.ToLibraryCommand;
import by.epam.booking.command.impl.guest.user.*;
import by.epam.booking.command.impl.user.*;
import by.epam.booking.command.impl.user.change.*;

public enum CommandEnumeration {

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
    }
    ;

    WebCommand command;
    public WebCommand getCurrentCommand() {
        return command;
    }
}
