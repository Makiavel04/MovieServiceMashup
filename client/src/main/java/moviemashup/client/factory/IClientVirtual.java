package moviemashup.client.factory;

import moviemashup.virtual.dto.VirtualServiceMovieDTO;
import moviemashup.virtual.exception.VirtualServiceMovieNotFoundException;

import java.io.IOException;

public interface IClientVirtual {
    VirtualServiceMovieDTO findMovieInformation(String title) throws IOException, VirtualServiceMovieNotFoundException;
}
