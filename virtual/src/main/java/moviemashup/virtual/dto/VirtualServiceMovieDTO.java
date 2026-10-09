package moviemashup.virtual.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import moviemashup.tmdb.CharacterDto;

import java.net.URL;
import java.util.List;

public class VirtualServiceMovieDTO {
    @JsonProperty("title")
    private String title;

    @JsonProperty("genre")
    private List<String> genre;

    @JsonProperty("poster_path")
    private URL poster_path;

    @JsonProperty("year")
    private int year;

    @JsonProperty("characters")
    private List<CharacterDto> characters;

    @JsonProperty("points")
    private short points;

    @JsonProperty("visualisationDate")
    private String visualisationDate;


    public VirtualServiceMovieDTO(){}
    public VirtualServiceMovieDTO(String title, List<String> genre, URL poster_path, int year, List<CharacterDto> characters, short points, String visualisationDate){
        this.title = title;
        this.genre = genre;
        this.poster_path = poster_path;
        this.year = year;
        this.characters = characters;
        this.points = points;
        this.visualisationDate = visualisationDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    public URL getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(URL poster_path) {
        this.poster_path = poster_path;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<CharacterDto> getCharacters() {
        return characters;
    }

    public void setCharacters(List<CharacterDto> characters) {
        this.characters = characters;
    }

    public short getPoints() {
        return points;
    }

    public void setPoints(short points) {
        this.points = points;
    }

    public String getVisualisationDate() {
        return visualisationDate;
    }

    public void setVisualisationDate(String visualisationDate) {
        this.visualisationDate = visualisationDate;
    }

    @Override
    public String toString() {
        return "VirtualServiceMovieDTO{\n" +
                "title='" + title + '\'' +
                ",\n genre=" + genre +
                ",\n poster_path=" + poster_path +
                ",\n year=" + year +
                ",\n characters=" + characters +
                ",\n points=" + points +
                ",\n visualisationDate='" + visualisationDate + '\'' +
                "\n}";
    }
}
