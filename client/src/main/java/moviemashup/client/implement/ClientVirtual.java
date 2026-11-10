package moviemashup.client.implement;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import moviemashup.client.dto.ClientMovieDTO;
import moviemashup.client.exception.ClientMovieNotFoundException;
import moviemashup.client.factory.IClientVirtual;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Properties;

public class ClientVirtual implements IClientVirtual {

    private String url;
    private ObjectMapper mapper;

    public ClientVirtual() {
        Properties props = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("client-conf.properties")) {
            props.load(input);
            url = props.getProperty("movieVirtualUrl");
        } catch (IOException ex) {
            throw new RuntimeException();
        }
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public ClientMovieDTO findMovieInformation(String title) throws IOException, ClientMovieNotFoundException {
        ClassicHttpResponse response = (ClassicHttpResponse) Request.get(url +"/movie/find?title="
                            + URLEncoder.encode(title, "UTF-8")).
                    execute().returnResponse();

        int statusCode = response.getCode();

        if (statusCode == HttpStatus.SC_NOT_FOUND) {
            throw new ClientMovieNotFoundException(title);
        }

        if (statusCode < 200 || statusCode >= 300) {
            String body;
            try{
                body = EntityUtils.toString(response.getEntity());
            }catch(ParseException e){
                throw new IOException("Unexpected error : error while reading the error response.");
            }
            throw new IOException("Unexpected error : " + body);
        }

        return mapper.readValue(response.getEntity().getContent(), ClientMovieDTO.class);
    }
}
