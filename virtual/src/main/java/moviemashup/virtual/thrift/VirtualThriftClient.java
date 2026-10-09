package moviemashup.virtual.thrift;

import moviemashup.thrift.MovieDto;
import moviemashup.thrift.MovieService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class VirtualThriftClient {
    public MovieDto findMovieByTitle(String title) throws TException {
        MovieService.Client client = getClient();
        TTransport transport = client.getInputProtocol().getTransport();

        try { transport.open();}
        catch (TTransportException e) {throw new RuntimeException(e);}
        TProtocol protocol = new  TBinaryProtocol(transport);

        transport.close();
        return client.findMovieByTitle(title);
    }

    private static MovieService.Client getClient() {

        try {
            TTransport transport = new THttpClient("http://localhost:8080/thrift/thrift/movie");
            TProtocol protocol = new TBinaryProtocol(transport);

            return new MovieService.Client(protocol);

        } catch (TTransportException e) {
            throw new RuntimeException(e);
        }

    }
}
