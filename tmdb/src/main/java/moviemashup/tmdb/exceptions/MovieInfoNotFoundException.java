package moviemashup.tmdb.exceptions;

public class MovieInfoNotFoundException extends Exception {
    public MovieInfoNotFoundException(String title) {
        super("The information on the movie " + title + " was not found.");
    }
}
