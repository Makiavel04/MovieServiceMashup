package moviemashup.client.factory;

import moviemashup.client.dto.ClientMovieDTO;
import moviemashup.client.exception.ClientMovieNotFoundException;

import java.io.IOException;

public interface IClientVirtual {
    ClientMovieDTO findMovieInformation(String title) throws IOException, ClientMovieNotFoundException;
}
