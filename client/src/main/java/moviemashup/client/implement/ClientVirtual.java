package moviemashup.client.implement;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import moviemashup.client.factory.IClientVirtual;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.core5.http.ClassicHttpResponse;
import moviemashup.virtual.dto.VirtualServiceMovieDTO;
import moviemashup.virtual.exception.VirtualServiceMovieNotFoundException;
import org.apache.hc.core5.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Properties;

public class ClientVirtual implements IClientVirtual {

    private String url;
    private ObjectMapper mapper;

    public ClientVirtual() {
        Properties props = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("conf.properties")) {
            props.load(input);
            url = props.getProperty("movieVirtualUrl");
        } catch (IOException ex) {
            throw new RuntimeException();
        }
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public VirtualServiceMovieDTO findMovieInformation(String title) throws IOException, VirtualServiceMovieNotFoundException {
        ClassicHttpResponse response = (ClassicHttpResponse) Request.get(url +"/movie/find?title="
                            + URLEncoder.encode(title, "UTF-8")).
                    execute().returnResponse();

        int statusCode = response.getCode();
        if (statusCode == HttpStatus.SC_NOT_FOUND) {
            throw new VirtualServiceMovieNotFoundException(title);
        }

        if (statusCode < 200 || statusCode >= 300) {
            throw new IOException("Erreur API inattendue : " + statusCode);
        }

        return mapper.readValue(response.getEntity().getContent(), VirtualServiceMovieDTO.class);
    }
}
