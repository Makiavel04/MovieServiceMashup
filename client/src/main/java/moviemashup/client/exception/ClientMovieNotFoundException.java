package moviemashup.client.exception;

public class ClientMovieNotFoundException extends Exception {
    public ClientMovieNotFoundException(String message) {
        super("Error ClientMovieNotFoundException : "+message);
    }
}
