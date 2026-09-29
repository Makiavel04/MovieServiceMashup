package moviemashup.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {
    private String title;
    private short year;
    @JsonProperty("visualisationinfo")
    private VisualisationInfo visualisationInfo;

    public Movie(){
        super();
    }

    public Movie(short year, String title, VisualisationInfo visualisationInfo) {
        this.year = year;
        this.title = title;
        this.visualisationInfo = visualisationInfo;
    }

    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public VisualisationInfo getVisualisationInfo() {
        return visualisationInfo;
    }

    public void setVisualisationInfo(VisualisationInfo visualisationInfo) {
        this.visualisationInfo = visualisationInfo;
    }
}
