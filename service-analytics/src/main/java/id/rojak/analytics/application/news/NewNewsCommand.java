package id.rojak.analytics.application.news;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by imrenagi on 7/18/17.
 */
public class NewNewsCommand {

    @NotNull
    @JsonProperty("title")
    private String title;

    @NotNull
    @JsonProperty("url")
    private String url;

    @NotNull
    @JsonProperty("content")
    private String content;

    @NotNull
    @JsonProperty("timestamp")
    private Long timestamp;

    @JsonProperty("sentiments")
    private List<CandidateSentimentCommand> sentiments;

    protected NewNewsCommand() {}

    public NewNewsCommand(String title,
                          String url,
                          String content,
                          Long timestamp,
                          List<CandidateSentimentCommand> sentiments) {
        this.title = title;
        this.url = url;
        this.content = content;
        this.timestamp = timestamp;
        this.sentiments = sentiments;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getContent() {
        return content;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public List<CandidateSentimentCommand> getSentiments() {
        return sentiments;
    }
}
