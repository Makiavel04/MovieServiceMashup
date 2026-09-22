package moviemashup.model;

import java.util.Date;

public class VisualisationInfo {
    private Date visualisationDate;
    private short punctuation;

    public VisualisationInfo(short punctuation, Date visualisationDate) {
        this.punctuation = punctuation;
        this.visualisationDate = visualisationDate;
    }

    public Date getVisualisationDate() {
        return visualisationDate;
    }

    public void setVisualisationDate(Date visualisationDate) {
        this.visualisationDate = visualisationDate;
    }

    public short getPunctuation() {
        return punctuation;
    }

    public void setPunctuation(short punctuation) {
        this.punctuation = punctuation;
    }
}
