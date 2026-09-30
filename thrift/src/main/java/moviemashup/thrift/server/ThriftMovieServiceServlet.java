package moviemashup.thrift.server;

import moviemashup.thrift.MovieService;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;

public class ThriftMovieServiceServlet extends ThriftHttpServletTemplate{


    public ThriftMovieServiceServlet() {
        super(createProcessor(), createProtocolFactory());
    }

    private static TProcessor createProcessor() {

        return new MovieService.Processor<MovieService.Iface>(
                new MovieServiceImpl());

    }

    private static TProtocolFactory createProtocolFactory() {
        return new TBinaryProtocol.Factory();
    }

}