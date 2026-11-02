package moviemashup.virtual.thrift;

import moviemashup.thrift.MovieDto;
import moviemashup.thrift.MovieService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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
        String THRIFT_BASE_URL;
        try (InputStream input = VirtualThriftClient.class.getResourceAsStream("/tmdb-conf.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            THRIFT_BASE_URL  = prop.getProperty("API_BASE_URL");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {

            TTransport transport = new THttpClient(THRIFT_BASE_URL + "/movie");
            TProtocol protocol = new TBinaryProtocol(transport);

            return new MovieService.Client(protocol);

        } catch (TTransportException e) {
            throw new RuntimeException(e);
        }

    }
}
