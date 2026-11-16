package moviemashup.tmdb.client;

import moviemashup.tmdb.dto.MovieInfoDto;
import moviemashup.tmdb.exceptions.MovieInfoNotFoundException;

public interface MovieInformationClient {
    MovieInfoDto findMovieInformation(String title) throws MovieInfoNotFoundException;
}
