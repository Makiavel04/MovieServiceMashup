package moviemashup.tmdb;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import moviemashup.tmdb.exceptions.MovieInfoNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class TMDbClientImpl implements MovieInformationClient {
    @Override
    public MovieInfoDto findMovieInformation(String title) throws MovieInfoNotFoundException {
        String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";
        MovieInfoDto movieInfoDto = new MovieInfoDto();
        ObjectMapper objectMapper = new ObjectMapper();

        String API_KEY;

        try (InputStream input = TMDbClientImpl.class.getResourceAsStream("/conf.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            API_KEY = prop.getProperty("API_KEY");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String genresUrl = "https://api.themoviedb.org/3/genre/movie/list?api_key=" + API_KEY;
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(genresUrl)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonNode rootNode = objectMapper.readTree(response.body());
            JsonNode genresNode = rootNode.path("genres");
            Map<Integer, String> genreMap = new HashMap<>();
            if (genresNode.isArray()) {
                for (JsonNode genreNode : genresNode) {
                    int id = genreNode.path("id").asInt();
                    String name = genreNode.path("name").asText();
                    genreMap.put(id, name);
                }
            }
            title = URLEncoder.encode(title, StandardCharsets.UTF_8);
            String searchUrl = "https://api.themoviedb.org/3/search/movie?query=" + title + "&api_key=" + API_KEY;
            request = HttpRequest.newBuilder().uri(URI.create(searchUrl)).GET().build();
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            rootNode = objectMapper.readTree(response.body());
            JsonNode movieNode = rootNode.path("results").get(0);

            String movieTitle = movieNode.path("title").asText();
            movieInfoDto.setTitle(movieTitle);

            String posterPath = movieNode.path("poster_path").asText();
            movieInfoDto.setPoster_path(URI.create(IMAGE_BASE_URL + posterPath).toURL());

            String releaseDate = movieNode.path("release_date").asText();
            short year = Short.parseShort(releaseDate.substring(0, 4));
            movieInfoDto.setYear(year);

            List<String> genres = new ArrayList<>();
            JsonNode genreIdsNode = movieNode.path("genre_ids");
            for (JsonNode idNode : genreIdsNode) {
                int genreId = idNode.asInt();
                String genreName = genreMap.get(genreId);
                if (genreName != null) {
                    genres.add(genreName);
                }
            }

            movieInfoDto.setGenre(genres);

            String movieId = movieNode.path("id").asText();
            String creditsUrl = "https://api.themoviedb.org/3/movie/" + movieId + "/credits?api_key=" + API_KEY;
            request = HttpRequest.newBuilder().uri(URI.create(creditsUrl)).GET().build();

            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            rootNode = objectMapper.readTree(response.body());
            JsonNode castNode = rootNode.path("cast");
            List<CharacterDto> characters = new ArrayList<>();
            if (castNode.isArray()) {
                for (JsonNode castMember : castNode) {
                    CharacterDto characterDto = new CharacterDto();

                    characterDto.setCharacterName(castMember.path("character").asText());

                    String personId = castMember.path("id").asText();
                    String personUrl = "https://api.themoviedb.org/3/person/" + personId + "?api_key=" + API_KEY;
                    request = HttpRequest.newBuilder().uri(URI.create(personUrl)).GET().build();
                    response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    rootNode = objectMapper.readTree(response.body());

                    characterDto.setActorName(rootNode.path("name").asText());

                    characterDto.setBirthday(rootNode.path("birthday").asText());

                    if (!rootNode.path("deathday").isNull()) {
                        characterDto.setDeathday(rootNode.path("deathday").asText());
                    }

                    characterDto.setPlaceOfBirth(rootNode.path("place_of_birth").asText());

                    String profilePath = rootNode.path("profile_path").asText();
                    if (!rootNode.path("profile_path").isNull()) {
                        characterDto.setImgurl(URI.create(IMAGE_BASE_URL + profilePath).toURL());
                    } else {
                        characterDto.setImgurl(null);
                    }

                    characters.add(characterDto);
                }
            }
            movieInfoDto.setCharacters(characters);

        } catch (Exception e) {
            throw new MovieInfoNotFoundException(title);
        }
        return movieInfoDto;
    }

}
