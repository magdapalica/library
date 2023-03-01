package pl.javastart.library.exception;

public class UserAlreadyEistException extends RuntimeException {
    public UserAlreadyEistException(String message) {
        super(message);
    }
}
