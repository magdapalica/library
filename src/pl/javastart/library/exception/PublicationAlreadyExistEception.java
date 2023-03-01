package pl.javastart.library.exception;

public class PublicationAlreadyExistEception extends RuntimeException {
    public PublicationAlreadyExistEception(String message) {
        super(message);
    }
}
