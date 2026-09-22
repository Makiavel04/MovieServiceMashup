package moviemashup.model;

import java.util.Date;
import java.util.List;

public interface MovieModel {
    void addMovie(String title, short year, Date visualisationDate, short punctuation);
    Movie findMovieByTitle(String title) throws MovieNotFoundException;
    List<Movie> findMoviesByYear(short year);
}
