package moviemashup.model;

public class MovieModelFactory {

    private static MovieModel model;

    private MovieModelFactory(){
        super();
    }
    public static MovieModel getModel(){
        if(model != null){
            return model;
        }
        return new MovieModelImpl();
    }
}
