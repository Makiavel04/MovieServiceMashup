package moviemashup.model.moviemodel;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sun.tools.javac.Main;
import moviemashup.model.entities.Movie;
import moviemashup.model.entities.VisualisationInfo;
import moviemashup.model.exception.MovieNotFoundException;

public class MovieModelImpl implements MovieModel{
    private List<Movie> movies;

    public MovieModelImpl() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try (InputStream is = Main.class.getClassLoader().getResourceAsStream("movies_100.json")) {
            this.movies = mapper.readValue(is, new TypeReference<>(){});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
