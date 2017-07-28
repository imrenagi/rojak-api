package id.rojak.analytics.resource.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by imrenagi on 7/19/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewsDTO {

    @JsonProperty("id")
    private String id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("url")
    private String url;
    @JsonProperty("content")
    private String content;
    @JsonProperty("timestamp")
    private Long timestamp;
    @JsonProperty("sentiments")
    private List<String> sentiments;

    protected NewsDTO() {
    }

    public NewsDTO(
            String id,
            String title,
            String url,
            String content,
            Long timestamp,
            List<String> sentiments) {
        this();

        this.id = id;
        this.title = title;
        this.url = url;
        this.content = content;
        this.timestamp = timestamp;
        this.sentiments = sentiments;
    }
}
