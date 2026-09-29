package moviemashup.thrift.server;

import moviemashup.model.entities.VisualisationInfo;
import moviemashup.thrift.MovieDto;
import moviemashup.model.entities.Movie;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MovieToThriftMovieDtoConverter {
    public static List<MovieDto> toMovieDtos(List<Movie> movies){
        List<MovieDto> movieDtos = new ArrayList<>(movies.size());
        for(Movie m : movies){
            movieDtos.add(toMovieDto(m));
        }
        return movieDtos;
    }

    public static MovieDto toMovieDto(Movie movie){
        String visuDate; short pts;
        if(movie.getVisualisationInfo() != null){
            visuDate = movie.getVisualisationInfo().getVisualisationDate()!=null ? movie.getVisualisationInfo().getVisualisationDate().toString() : "";
            pts = movie.getVisualisationInfo().getPunctuation();
        }else{
            visuDate = "";
            pts = -1;
        }
        return new MovieDto(movie.getTitle(), movie.getYear(), visuDate, pts);
    }

    public static Movie toMovie(MovieDto movie) {
        VisualisationInfo vi;
        if((movie.getPoints()!=-1) && !movie.getVisualisationDate().isEmpty()){
            vi = new VisualisationInfo(movie.getPoints(), LocalDate.parse(movie.getVisualisationDate()));
        }else{
            vi = null;
        }
        return new Movie(movie.getYear(), movie.getTitle(), vi);
    }
}
