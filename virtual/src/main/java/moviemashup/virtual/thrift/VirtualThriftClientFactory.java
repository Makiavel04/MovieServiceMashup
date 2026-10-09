package moviemashup.virtual.thrift;

import java.io.InputStream;
import java.util.Properties;

public class VirtualThriftClientFactory {

    private static VirtualThriftClient service = null;

    private VirtualThriftClientFactory() {
    }

    @SuppressWarnings("rawtypes")
    private static VirtualThriftClient getInstance() {

        try (InputStream input = VirtualThriftClient.class.getResourceAsStream("/conf.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            Class serviceClass = Class.forName(prop.getProperty("model"));
            return (VirtualThriftClient) serviceClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public synchronized static VirtualThriftClient getClient() {
        if (service == null) {
            service = getInstance();
        }
        return service;

    }
}