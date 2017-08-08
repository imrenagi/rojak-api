package id.rojak.analytics.resource.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    private CandidateDTO candidate;
    private MediaNewsCountDTO totalNews;


    public MediaDTO(String id,
                    String name,
                    String webUrl,
                    String logoUrl) {
        this.id = id;
        this.name = name;
        this.webUrl = webUrl;
        this.logoUrl = logoUrl;
    }

    public MediaDTO(String id,
                    String name,
                    String webUrl,
                    String logoUrl,
                    MediaNewsCountDTO totalNews) {
        this(id, name, webUrl, logoUrl);

        this.setTotalNews(totalNews);
    }

    public MediaDTO(String id,
                    String name,
                    String webUrl,
                    String logoUrl,
                    CandidateDTO candidate) {
        this(id, name, webUrl, logoUrl);

        this.setCandidate(candidate);
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

    @JsonProperty("total_news")
    public MediaNewsCountDTO getTotalNews() {
        return totalNews;
    }

    @JsonProperty("total_news")
    public void setTotalNews(MediaNewsCountDTO totalNews) {
        this.totalNews = totalNews;
    }

    @JsonProperty("candidate")
    public CandidateDTO getCandidate() {
        return this.candidate;
    }

    @JsonProperty("candidate")
    public void setCandidate(CandidateDTO candidate) {
        this.candidate = candidate;
    }
}
