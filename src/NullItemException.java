public class NullItemException extends RuntimeException {

    public NullItemException() {
    }

    public NullItemException(String message) {
        super(message);
    }

    public NullItemException(String message, Throwable cause) {
        super(message, cause);
    }
}
