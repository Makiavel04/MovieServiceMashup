package moviemashup.model.exception;

public class MovieNotFoundException extends Exception {
    public MovieNotFoundException(String title) {
        super("Movie not found with title :"+title+".");
    }
}
