package moviemashup.model.moviemodel;

public class MovieModelFactory {

    private static MovieModel model;

    private MovieModelFactory(){
        super();
    }
    public static MovieModel getModel(){
        if(model != null){
            return model;
        }
        model = new MovieModelImpl();
        return model;
    }
}
