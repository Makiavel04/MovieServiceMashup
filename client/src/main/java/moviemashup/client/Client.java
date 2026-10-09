package moviemashup.client;

import moviemashup.client.factory.ClientThriftFactory;
import moviemashup.client.factory.ClientVirtualFactory;
import moviemashup.client.factory.IClientThrift;
import moviemashup.client.factory.IClientVirtual;
import org.apache.thrift.TException;

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

    /*public VirtualMovieDto findMovieInformation(String title){
        return clientVirtual.findMovieInformation(title);
    }*/


}
