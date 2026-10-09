package moviemashup.client.implement;

import moviemashup.client.factory.IClientThrift;
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

public class ClientThrift implements IClientThrift {

    private MovieService.Client clientThrift;
    
    public ClientThrift() {
        Properties props = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("conf.properties")) {
            props.load(input);
        } catch (IOException ex) {
            throw new RuntimeException();
        }
        try {
            TTransport trans = new THttpClient(props.getProperty("movieThriftUrl"));
            TProtocol prot = new TBinaryProtocol(trans);
            this.clientThrift = new MovieService.Client.Factory().getClient(prot);
        } catch (TTransportException e) {
            throw new RuntimeException(e);
        }
    }

    public void addMovie(String title, short year, String visualisationDate, short points) throws TException {
        clientThrift.addMovie(new MovieDto(title,year,visualisationDate,points));
    }

}
