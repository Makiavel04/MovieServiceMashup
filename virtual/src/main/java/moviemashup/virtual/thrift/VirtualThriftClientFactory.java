package moviemashup.virtual.thrift;

import java.io.InputStream;
import java.util.Properties;

public class VirtualThriftClientFactory {

    private static IVirtualThriftClient service = null;

    private VirtualThriftClientFactory() {
    }

    @SuppressWarnings("rawtypes")
    private static IVirtualThriftClient getInstance() {

        try (InputStream input = VirtualThriftClientImpl.class.getResourceAsStream("/virtual-conf.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            Class serviceClass = Class.forName(prop.getProperty("model"));
            return (VirtualThriftClientImpl) serviceClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public synchronized static IVirtualThriftClient getClient() {
        if (service == null) {
            service = getInstance();
        }
        return service;

    }
}