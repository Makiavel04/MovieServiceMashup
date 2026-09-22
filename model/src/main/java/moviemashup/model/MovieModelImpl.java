package moviemashup.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class MovieModelImpl implements MovieModel{
    private List<Movie> movies;

    public MovieModelImpl() {
        this.movies = new ArrayList<>();
    }

    @Override
    public void addMovie(String title, short year, LocalDate visualisationDate, short punctuation) {
        VisualisationInfo visualisationInfo = new VisualisationInfo(punctuation,visualisationDate);
        Movie movie = new Movie(year,title,visualisationInfo);
        movies.add(movie);
    }

    @Override
    public Movie findMovieByTitle(String title) throws MovieNotFoundException {
        for(Movie movie : movies){
            if(movie.getTitle().equals(title)){
                return movie;
            }
        }
        throw new MovieNotFoundException(title);
    }

    @Override
    public List<Movie> findMoviesByYear(short year) {
        List<Movie> moviesFind = new ArrayList<>();
        for(Movie movie : movies){
            if(movie.getYear() == year){
                moviesFind.add(movie);
            }
        }
        return moviesFind;
    }
}
