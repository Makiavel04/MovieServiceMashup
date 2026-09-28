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

    @Override
    public void addMovie(MovieDto movieDto) throws TException {
        //Movie movie = MovieToThriftMovieDtoConversor.toMovie(movieDto);
        MovieModel model = MovieModelFactory.getModel();
        model.addMovie(movieDto.getTitle(), movieDto.getYear(), LocalDate.parse(movieDto.getVisualisationDate()), movieDto.getPoints());
    }

    @Override
    public MovieDto findMovieByTitle(String title) throws ServiceMovieNotFoundException, TException {
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
        MovieModel model = MovieModelFactory.getModel();
        List<Movie> movies = model.findMoviesByYear(year);
        List<MovieDto> movieDtos = MovieToThriftMovieDtoConversor.toMovieDtos(movies);
        return movieDtos;
    }
}
