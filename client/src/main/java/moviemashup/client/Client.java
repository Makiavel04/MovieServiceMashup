package moviemashup.client;

import moviemashup.client.dto.ClientMovieDTO;
import moviemashup.client.exception.ClientMovieNotFoundException;
import moviemashup.client.thrift.ClientThriftFactory;
import moviemashup.client.virtual.ClientVirtualFactory;
import moviemashup.client.thrift.IClientThrift;
import moviemashup.client.virtual.IClientVirtual;
import org.apache.thrift.TException;
import java.io.IOException;

public class Client {
    private IClientThrift clientThrift;
    private IClientVirtual clientVirtual;

    public Client() {
        this.clientThrift = ClientThriftFactory.getClientThrift();
        this.clientVirtual = ClientVirtualFactory.getClientVirtual();
    }

    public void addMovie(String title, short year, String visualisationDate, short points) throws TException {
        clientThrift.addMovie(title, year, visualisationDate, points);
    }

    public ClientMovieDTO findMovieInformation(String title) throws IOException, ClientMovieNotFoundException {
        return clientVirtual.findMovieInformation(title);
    }

}
