package moviemashup.tmdb;

import moviemashup.tmdb.exceptions.MovieInfoNotFoundException;

public interface MovieInformationClient {
    MovieInfoDto findMovieInformation(String title) throws MovieInfoNotFoundException;
}
