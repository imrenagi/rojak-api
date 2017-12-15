package id.rojak.analytics.resource.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by imrenagi on 7/19/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewsCollectionDTO {

    @JsonProperty("news")
    private List<NewsDTO> news;
    @JsonProperty("meta")
    private MetaDTO meta;

    public NewsCollectionDTO(List<NewsDTO> news, MetaDTO meta) {
        this.news = news;
        this.meta = meta;
    }
}
