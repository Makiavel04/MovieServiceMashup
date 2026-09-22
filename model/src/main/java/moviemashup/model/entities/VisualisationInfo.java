package moviemashup.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class VisualisationInfo {
    @JsonProperty("date")
    private LocalDate visualisationDate;
    @JsonProperty("rating")
    private short punctuation;

    public VisualisationInfo() {
        super();
    }
    public VisualisationInfo(short punctuation, LocalDate visualisationDate) {
        this.punctuation = punctuation;
        this.visualisationDate = visualisationDate;
    }

    public LocalDate getVisualisationDate() {
        return visualisationDate;
    }

    public void setVisualisationDate(LocalDate visualisationDate) {
        this.visualisationDate = visualisationDate;
    }

    public short getPunctuation() {
        return punctuation;
    }

    public void setPunctuation(short punctuation) {
        this.punctuation = punctuation;
    }
}
