package by.epam.booking.exception;

public class SpecificationException extends Exception {
    public SpecificationException() {
        super();
    }

    public SpecificationException(String s) {
        super(s);
    }

    public SpecificationException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public SpecificationException(Throwable throwable) {
        super(throwable);
    }
}
