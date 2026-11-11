package moviemashup.virtual.thrift;

import moviemashup.thrift.MovieDto;
import org.apache.thrift.TException;

public interface IVirtualThriftClient {
    public MovieDto findMovieByTitle(String title) throws TException;
}
