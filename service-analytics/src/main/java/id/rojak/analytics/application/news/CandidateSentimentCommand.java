package id.rojak.analytics.application.news;

import com.fasterxml.jackson.annotation.JsonProperty;
import id.rojak.analytics.domain.model.ValueObject;

import javax.validation.constraints.NotNull;

/**
 * Created by imrenagi on 7/19/17.
 */
public class CandidateSentimentCommand extends ValueObject {

    @NotNull
    @JsonProperty("candidate_id")
    private String candidateId;

    @NotNull
    @JsonProperty("sentiment")
    private String sentiment;

    protected CandidateSentimentCommand() {
    }

    public CandidateSentimentCommand(String candidateId,
                                     String sentiment) {
        this.candidateId = candidateId;
        this.sentiment = sentiment;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public String getSentiment() {
        return sentiment;
    }
}
