package moviemashup.model.moviemodel;

import java.io.InputStream;
import java.util.Properties;

public class MovieModelFactory {
    private static MovieModel model = null;

    private MovieModelFactory(){
        super();
    }
    @SuppressWarnings("rawtypes")
    private static MovieModel getInstance() {
        try (InputStream input = MovieModelFactory.class.getResourceAsStream("/conf.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            Class serviceClass = Class.forName(prop.getProperty("model"));
            return (MovieModel) serviceClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public synchronized static MovieModel getModel(){
        if (model == null) {
            model = getInstance();
        }
        return model;
    }
}
