package id.rojak.analytics.application.news;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by inagi on 7/27/17.
 */
public class InsertSentimentCommand {

    @JsonProperty("sentiments")
    List<CandidateSentimentCommand> sentiments;

    public InsertSentimentCommand(){}

    public InsertSentimentCommand(List<CandidateSentimentCommand> sentiments) {
        this();

        this.sentiments = sentiments;
    }

    public List<CandidateSentimentCommand> sentiments() {
        return this.sentiments;
    }
}
