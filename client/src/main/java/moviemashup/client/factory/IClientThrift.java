package moviemashup.client.factory;

import org.apache.thrift.TException;

public interface IClientThrift {
    void addMovie(String title, short year, String visualisationDate, short points) throws TException;
}
