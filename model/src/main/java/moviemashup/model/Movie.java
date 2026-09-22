package moviemashup.model;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String title;
    private short year;
    private List<VisualisationInfo> visualisationInfoList;

    public Movie(short year, String title) {
        this.year = year;
        this.title = title;
        this.visualisationInfoList = new ArrayList<>();
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

    public List<VisualisationInfo> getVisualisationInfoList() {
        return visualisationInfoList;
    }

    public void setVisualisationInfoList(List<VisualisationInfo> visualisationInfoList) {
        this.visualisationInfoList = visualisationInfoList;
    }
}
