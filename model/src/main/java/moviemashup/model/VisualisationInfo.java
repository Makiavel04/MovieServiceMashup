package moviemashup.model;

import java.time.LocalDate;

public class VisualisationInfo {
    private LocalDate visualisationDate;
    private short punctuation;

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
