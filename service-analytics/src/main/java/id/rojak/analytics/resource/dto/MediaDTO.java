package id.rojak.analytics.resource.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by imrenagi on 7/18/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MediaDTO {

    private String id;
    private String name;
    private String webUrl;
    private String logoUrl;

    @JsonProperty("statistic")
    private StatisticDTO statistic;

    @JsonProperty("candidates")
    private List<CandidateStatisticDTO> candidateStatistics;

    @JsonProperty("related_tags")
    private List<String> relatedTags;

    public MediaDTO(String id,
                    String name,
                    String webUrl,
                    String logoUrl) {
        this.id = id;
        this.name = name;
        this.webUrl = webUrl;
        this.logoUrl = logoUrl;
        this.relatedTags = new ArrayList<>();
    }

    public MediaDTO(String id,
                    String name,
                    String webUrl,
                    String logoUrl,
                    StatisticDTO statistic) {
        this(id, name, webUrl, logoUrl);

        this.statistic = statistic;
    }

    public MediaDTO(String id,
                    String name,
                    String webUrl,
                    String logoUrl,
                    List<CandidateStatisticDTO> candidatesStatistic) {
        this(id, name, webUrl, logoUrl);

        this.candidateStatistics = candidatesStatistic;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("web_url")
    public String getWebUrl() {
        return webUrl;
    }

    @JsonProperty("web_url")
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    @JsonProperty("logo_url")
    public String getLogoUrl() {
        return logoUrl;
    }

    @JsonProperty("logo_url")
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }




}
