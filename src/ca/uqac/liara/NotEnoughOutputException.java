package ca.uqac.liara;

/**
 * Created by Baptiste on 10/11/2016.
 */
public class NotEnoughOutputException extends Exception {
    public NotEnoughOutputException() {
    }

    public NotEnoughOutputException(String message) {
        super(message);
    }

    public NotEnoughOutputException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughOutputException(Throwable cause) {
        super(cause);
    }
}
