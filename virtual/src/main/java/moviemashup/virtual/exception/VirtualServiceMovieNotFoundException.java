package moviemashup.virtual.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Movie not found")
public class VirtualServiceMovieNotFoundException extends Exception{
    public VirtualServiceMovieNotFoundException(String title) {
        super("No movie found for the title :"+title+".");
    }

}
