package moviemashup.virtual.service;

import moviemashup.virtual.dto.VirtualServiceMovieDTO;

public interface VirtualMoviesInformationService {
    public VirtualServiceMovieDTO findMovieInformation(String title);
}
