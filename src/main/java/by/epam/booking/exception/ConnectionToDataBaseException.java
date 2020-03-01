package by.epam.booking.exception;

public class ConnectionToDataBaseException extends Exception {

    public ConnectionToDataBaseException() {
    }

    public ConnectionToDataBaseException(String s) {
        super(s);
    }

    public ConnectionToDataBaseException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ConnectionToDataBaseException(Throwable throwable) {
        super(throwable);
    }
}
