package moviemashup.virtual.service;

import moviemashup.virtual.dto.VirtualServiceMovieDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
public class VirtualMoviesInformationServiceImpl implements VirtualMoviesInformationService{

    @Override
    @RequestMapping(value="/find", method= RequestMethod.GET)
    @ResponseBody
    public VirtualServiceMovieDTO findMovieInformation(@RequestParam(name="title") String title) {

    }
}
