package moviemashup.model.moviemodel;

import moviemashup.model.entities.Movie;
import moviemashup.model.exception.MovieNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface MovieModel {
    void addMovie(String title, short year, LocalDate visualisationDate, short punctuation);
    Movie findMovieByTitle(String title) throws MovieNotFoundException;
    List<Movie> findMoviesByYear(short year);
}
