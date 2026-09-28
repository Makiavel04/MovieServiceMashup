package moviemashup.thrift.server;

import moviemashup.model.entities.VisualisationInfo;
import moviemashup.thrift.MovieDto;
import moviemashup.model.entities.Movie;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MovieToThriftMovieDtoConversor {
    public static List<MovieDto> toMovieDtos(List<Movie> movies){
        List<MovieDto> movieDtos = new ArrayList<>(movies.size());
        for(Movie m : movies){
            movieDtos.add(toMovieDto(m));
        }
        return movieDtos;
    }

    public static MovieDto toMovieDto(Movie movie){
        return new MovieDto(movie.getTitle(), movie.getYear(), movie.getVisualisationInfo().getVisualisationDate().toString(), movie.getVisualisationInfo().getPunctuation());
    }

    public static Movie toMovie(MovieDto movie) {
        return new Movie(movie.getYear(), movie.getTitle(), new VisualisationInfo(movie.getPoints(), LocalDate.parse(movie.getVisualisationDate())));
    }
}
