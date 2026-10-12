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
        String API_KEY;
        try (InputStream input = TMDbClientImpl.class.getResourceAsStream("/conf.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            API_KEY = prop.getProperty("API_KEY");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";
        String API_BASE_URL = "https://api.themoviedb.org/3/";
        HttpClient client = HttpClient.newHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();

        MovieInfoDto movieInfoDto = new MovieInfoDto();
        try {
            Map<Integer, String> genreMap = findMapGenres(client, objectMapper, API_KEY, API_BASE_URL);
            String movieId = updateMovieInfo(client, objectMapper, API_KEY, API_BASE_URL, IMAGE_BASE_URL, genreMap, movieInfoDto, title);
            updateMovieCharacters(client, objectMapper, API_KEY, API_BASE_URL, IMAGE_BASE_URL, movieInfoDto, movieId);
        } catch (Exception e) {
            throw new MovieInfoNotFoundException(title);
        }
        return movieInfoDto;
    }

    private Map<Integer, String> findMapGenres(HttpClient client, ObjectMapper objectMapper, String API_KEY, String API_BASE_URL) throws IOException, InterruptedException {
        String genresUrl = API_BASE_URL + "genre/movie/list?api_key=" + API_KEY;
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
        return genreMap;
    }

    private String updateMovieInfo(HttpClient client, ObjectMapper objectMapper, String API_KEY, String API_BASE_URL, String IMAGE_BASE_URL, Map<Integer, String> genreMap, MovieInfoDto movieInfoDto, String title) throws IOException, InterruptedException {
        title = URLEncoder.encode(title, StandardCharsets.UTF_8);
        String searchUrl = API_BASE_URL + "search/movie?query=" + title + "&api_key=" + API_KEY;
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(searchUrl)).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode rootNode = objectMapper.readTree(response.body());
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
        return movieNode.path("id").asText();
    }

    private void updateMovieCharacters(HttpClient client, ObjectMapper objectMapper, String API_KEY, String API_BASE_URL, String IMAGE_BASE_URL, MovieInfoDto movieInfoDto, String movieId) throws IOException, InterruptedException {
        String creditsUrl = API_BASE_URL + "movie/" + movieId + "/credits?api_key=" + API_KEY;

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(creditsUrl)).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode rootNode = objectMapper.readTree(response.body());
        JsonNode castNode = rootNode.path("cast");
        List<CharacterDto> characters = new ArrayList<>();
        if (castNode.isArray()) {
            for (JsonNode castMember : castNode) {
                CharacterDto characterDto = new CharacterDto();
                characterDto.setCharacterName(castMember.path("character").asText());
                String personId = castMember.path("id").asText();
                updateCharacterInfo(client, objectMapper, API_KEY, API_BASE_URL, IMAGE_BASE_URL, characterDto, personId);
                characters.add(characterDto);
            }
        }
        movieInfoDto.setCharacters(characters);


    }

    private void updateCharacterInfo(HttpClient client, ObjectMapper objectMapper, String API_KEY, String API_BASE_URL, String IMAGE_BASE_URL, CharacterDto characterDto, String personId) throws IOException, InterruptedException {

        String personUrl = API_BASE_URL + "person/" + personId + "?api_key=" + API_KEY;
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(personUrl)).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode rootNode = objectMapper.readTree(response.body());

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

    }
}
