package moviemashup.virtual.service;

import moviemashup.thrift.MovieDto;
import moviemashup.thrift.ServiceMovieNotFoundException;
import moviemashup.tmdb.MovieInfoDto;
import moviemashup.tmdb.MovieInformationClient;
import moviemashup.tmdb.MovieInformationClientFactory;
import moviemashup.tmdb.exceptions.MovieInfoNotFoundException;
import moviemashup.virtual.dto.VirtualServiceMovieDTO;
import moviemashup.virtual.exception.VirtualServiceMovieNotFoundException;
import moviemashup.virtual.thrift.VirtualThriftClient;
import moviemashup.virtual.thrift.VirtualThriftClientFactory;
import org.apache.thrift.TException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/movie")
public class VirtualMoviesInformationServiceImpl implements VirtualMoviesInformationService{

    @Override
    @RequestMapping(value="/find", method= RequestMethod.GET)
    @ResponseBody
    public VirtualServiceMovieDTO findMovieInformation(@RequestParam(name="title") String title) throws VirtualServiceMovieNotFoundException {
        MovieInformationClient clientTMDB = MovieInformationClientFactory.getClient();
        VirtualThriftClient clientThrift = VirtualThriftClientFactory.getClient();

        MovieInfoDto movieTMDB;
        MovieDto movieThrift;

        try{
            movieTMDB = clientTMDB.findMovieInformation(title);
            System.out.println("film TMDB ok");
        } catch (MovieInfoNotFoundException e) {
            movieTMDB = null;

            System.out.println("film TMDB Ko");
        }

        try{
            movieThrift = clientThrift.findMovieByTitle(title);
            System.out.println("film Thrift ok");
        }catch(ServiceMovieNotFoundException e){
            System.out.println("film Thrift ok");
            movieThrift = null;
        }catch(TException e){
            throw new RuntimeException(e);
        }

        if(movieThrift == null && movieTMDB == null){
            System.out.println("Movie not found");
            throw new VirtualServiceMovieNotFoundException(title);
        }

        movieThrift = movieThrift==null ? new MovieDto() : movieThrift;
        movieTMDB = movieTMDB==null ? new MovieInfoDto() : movieTMDB;

        String movieTitle = (movieTMDB.getTitle()!=null && !movieTMDB.getTitle().isEmpty()) ? movieTMDB.getTitle() : movieThrift.getTitle();
        short movieYear = (movieTMDB.getYear()!=-1) ? (short) movieTMDB.getYear() : movieThrift.getYear();
        VirtualServiceMovieDTO movie = new VirtualServiceMovieDTO(movieTitle , movieTMDB.getGenre(), movieTMDB.getPoster_path(), movieYear, movieTMDB.getCharacters(), movieThrift.getPoints(), movieThrift.getVisualisationDate());

        System.out.println(movie);

        return movie;
    }
}
