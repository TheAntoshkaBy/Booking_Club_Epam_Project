package by.epam.booking.exception;

public class PropertiesException extends Exception {
    public PropertiesException() {
    }

    public PropertiesException(String s) {
        super(s);
    }

    public PropertiesException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public PropertiesException(Throwable throwable) {
        super(throwable);
    }
}
