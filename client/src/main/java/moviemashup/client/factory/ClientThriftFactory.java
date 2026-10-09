package moviemashup.client.factory;

import java.io.InputStream;
import java.util.Properties;

public class ClientThriftFactory {

    private static IClientThrift clientThrift;

    private ClientThriftFactory(){
        super();
    }
    @SuppressWarnings("rawtypes")
    private static IClientThrift getInstance() {
        try (InputStream input = ClientThriftFactory.class.getResourceAsStream("/conf.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            Class serviceClass = Class.forName(prop.getProperty("clientThrift"));
            return (IClientThrift) serviceClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public synchronized static IClientThrift getClientThrift(){
        if (clientThrift == null) {
            clientThrift = getInstance();
        }
        return clientThrift;
    }
}
