package moviemashup.model;

import moviemashup.model.entities.Movie;
import moviemashup.model.exception.MovieNotFoundException;
import moviemashup.model.moviemodel.MovieModelFactory;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        /*MovieModelFactory.getModel().addMovie("test", (short) 2026, LocalDate.now(),(short)6);
        System.out.println("Test findMovieByTitle");
        try {
            Movie m = MovieModelFactory.getModel().findMovieByTitle("test");
            System.out.println(m.getTitle()+" "+m.getYear()+" "+m.getVisualisationInfo().getPunctuation()+" "+m.getVisualisationInfo().getVisualisationDate());
            MovieModelFactory.getModel().findMovieByTitle("Error");
        }catch (MovieNotFoundException e){
            System.err.println(e.getMessage());
        }


        System.out.println("Test findMoviesByYear");
        for(Movie movie : MovieModelFactory.getModel().findMoviesByYear((short) 2002)){
            System.out.println(movie.getTitle()+" "+movie.getYear()+" "+movie.getVisualisationInfo().getPunctuation()+" "+movie.getVisualisationInfo().getVisualisationDate());
        }*/
    }
}
