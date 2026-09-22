package moviemashup.tmdb;

public class MovieInformationClientFactory {

    private final static String MODEL_CLASS_NAME = "moviemashup.movies.tmdb.MovieInformationClient";

    private static MovieInformationClient service = null;

    private  MovieInformationClientFactory(){
    }

    @SuppressWarnings("rawtypes")
    private static TMDbClientImpl getInstance() {
        try {
            Class serviceClass = Class.forName(MODEL_CLASS_NAME);
            return (TMDbClientImpl) serviceClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public synchronized static MovieInformationClient getClient() {
        if (service == null) {
            service = getInstance();
        }
        return service;

    }
}
