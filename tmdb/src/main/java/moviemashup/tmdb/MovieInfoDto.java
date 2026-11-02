package moviemashup.tmdb;

import java.net.URL;
import java.util.List;

public class MovieInfoDto {
    private String title;

    private List<String> genre;

    private URL poster_path;

    private int year;

    private List<CharacterDto> characters;

    public MovieInfoDto() {
    }

    public MovieInfoDto(String title, List<String> genre, URL poster_path,
                        short year, List<CharacterDto> characters) {
        this.title = title;
        this.genre = genre;
        this.poster_path = poster_path;
        this.year = Short.toUnsignedInt(year);
        this.characters = characters;
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

    public void setYear(short year) {
        this.year = Short.toUnsignedInt(year);
    }

    public List<CharacterDto> getCharacters() {
        return characters;
    }

    public void setCharacters(List<CharacterDto> characters) {
        this.characters = characters;
    }

    @Override
    public String toString() {
        return "MovieInfoDto{" +
                "title='" + title + '\'' +
                ", genre=" + genre +
                ", poster_path=" + poster_path +
                ", year=" + year +
                ", characters=" + characters +
                '}';
    }
}