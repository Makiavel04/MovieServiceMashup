package moviemashup.client.factory;

import java.io.InputStream;
import java.util.Properties;

public class ClientVirtualFactory {
    private static IClientVirtual clientVirtual;

    private ClientVirtualFactory(){
        super();
    }
    @SuppressWarnings("rawtypes")
    private static IClientVirtual getInstance() {
        try (InputStream input = ClientVirtualFactory.class.getResourceAsStream("/conf.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            Class serviceClass = Class.forName(prop.getProperty("clientVirtual"));
            return (IClientVirtual) serviceClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public synchronized static IClientVirtual getClientVirtual(){
        if (clientVirtual == null) {
            clientVirtual = getInstance();
        }
        return clientVirtual;
    }
}
