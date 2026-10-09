package moviemashup.virtual.exception;

public class VirtualServiceMovieNotFoundException extends Exception{
    public VirtualServiceMovieNotFoundException(String title) {
        super("No movie found for the title :"+title+".");
    }

}
