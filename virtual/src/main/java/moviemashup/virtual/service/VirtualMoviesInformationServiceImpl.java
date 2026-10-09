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
        } catch (MovieInfoNotFoundException e) {
            movieTMDB = null;
        }

        try{
            movieThrift = clientThrift.findMovieByTitle(title);
        }catch(ServiceMovieNotFoundException e){
            movieThrift = null;
        }catch(TException e){
            throw new RuntimeException(e);
        }
        if(movieThrift == null && movieTMDB == null){
            throw new VirtualServiceMovieNotFoundException(title);
        }

        movieThrift = movieThrift==null ? new MovieDto() : movieThrift;
        movieTMDB = movieTMDB==null ? new MovieInfoDto() : movieTMDB;

        VirtualServiceMovieDTO movie = new VirtualServiceMovieDTO(movieTMDB.getTitle(), movieTMDB.getGenre(), movieTMDB.getPoster_path(), movieTMDB.getYear(), movieTMDB.getCharacters(), movieThrift.getPoints(), movieThrift.getVisualisationDate());

        return movie;
    }
}
