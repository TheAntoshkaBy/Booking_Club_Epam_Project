package by.epam.booking.filter.helper;

import java.util.ArrayList;

public class AllowedPages {
    public static void FillAllowLists(
            ArrayList<String> allowedPagesForGuest,
            ArrayList<String> allowedPagesForUser,
            ArrayList<String> allowedPagesForAdmin
    ){
        allowedPagesForGuest.add("login.jsp");
        allowedPagesForGuest.add("userPass.jsp");
        allowedPagesForGuest.add("book.jsp");
        allowedPagesForGuest.add("library.jsp");
        allowedPagesForGuest.add("planBook.jsp");
        allowedPagesForGuest.add("plans.jsp");
        allowedPagesForGuest.add("registration.jsp");
        allowedPagesForGuest.add("passwordConfirmation.jsp");
        allowedPagesForUser.addAll(allowedPagesForGuest);
        allowedPagesForUser.add("user.jsp");
        allowedPagesForUser.add("completedBooks.jsp");
        allowedPagesForAdmin.addAll(allowedPagesForUser);
        allowedPagesForAdmin.add("finance.jsp");
        allowedPagesForAdmin.add("usersList.jsp");
    }
}
