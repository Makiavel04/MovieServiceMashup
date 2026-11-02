package moviemashup.virtual.service;

import moviemashup.virtual.dto.VirtualServiceMovieDTO;
import moviemashup.virtual.exception.VirtualServiceMovieNotFoundException;

public interface VirtualMoviesInformationService {
    public VirtualServiceMovieDTO findMovieInformation(String title) throws VirtualServiceMovieNotFoundException;
}
