package moviemashup.tmdb;

import java.io.InputStream;
import java.util.Properties;

public class MovieInformationClientFactory {

    private static MovieInformationClient service = null;

    private MovieInformationClientFactory() {
    }

    @SuppressWarnings("rawtypes")
    private static TMDbClientImpl getInstance() {

        try (InputStream input = TMDbClientImpl.class.getResourceAsStream("/conf.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            Class serviceClass = Class.forName(prop.getProperty("model"));
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
