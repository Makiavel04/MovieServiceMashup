package moviemashup.thrift.server;

import moviemashup.model.entities.Movie;
import moviemashup.model.exception.MovieNotFoundException;
import moviemashup.model.moviemodel.MovieModel;
import moviemashup.model.moviemodel.MovieModelFactory;
import moviemashup.thrift.MovieDto;
import moviemashup.thrift.MovieService;
import moviemashup.thrift.ServiceMovieNotFoundException;
import org.apache.thrift.TException;

import java.time.LocalDate;
import java.util.List;

public class MovieServiceImpl implements MovieService.Iface {
    public static void logMovieServiceThrift(String msg){
        System.out.println("Thrift Service : " + msg);
    }
    @Override
    public void addMovie(MovieDto movieDto) throws TException {
        //Movie movie = MovieToThriftMovieDtoConversor.toMovie(movieDto);
        logMovieServiceThrift("Requested addMovie : " + movieDto.getTitle());
        MovieModel model = MovieModelFactory.getModel();
        model.addMovie(movieDto.getTitle(), movieDto.getYear(), LocalDate.parse(movieDto.getVisualisationDate()), movieDto.getPoints());
    }

    @Override
    public MovieDto findMovieByTitle(String title) throws ServiceMovieNotFoundException, TException {
        logMovieServiceThrift("Requested findMovie : " + title);
        MovieModel model = MovieModelFactory.getModel();
        try {
            Movie movie = model.findMovieByTitle(title);
            return MovieToThriftMovieDtoConversor.toMovieDto(movie);
        } catch (MovieNotFoundException e) {
            throw new ServiceMovieNotFoundException(e.getMessage());
        }
    }

    @Override
    public List<MovieDto> findMoviesByYear(short year) throws TException {
        logMovieServiceThrift("Requested Movies by year : " + Integer.toString(year));

        MovieModel model = MovieModelFactory.getModel();
        logMovieServiceThrift("after getModel");
        List<Movie> movies = model.findMoviesByYear(year);
        logMovieServiceThrift("after find");
        List<MovieDto> movieDtos = MovieToThriftMovieDtoConversor.toMovieDtos(movies);
        logMovieServiceThrift("before return");
        return movieDtos;
    }
}
