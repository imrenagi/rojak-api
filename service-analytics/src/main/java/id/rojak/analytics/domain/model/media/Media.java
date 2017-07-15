package id.rojak.analytics.domain.model.media;

import id.rojak.analytics.common.domain.model.IdentifiedDomainObject;
import id.rojak.analytics.domain.model.news.News;

import javax.persistence.*;
import java.util.List;

/**
 * Created by imrenagi on 7/14/17.
 */
@Entity
@Table(name="tbl_medias")
public class Media extends IdentifiedDomainObject {

    @Embedded
    private MediaId mediaId;

    @Column(name="name")
    private String name;

    @Column(name="website_url")
    private String websiteUrl;

    @Column(name="mobile_website_url")
    private String mobileWebsiteUrl;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "social_media_id")
    private SocialMedia socialMedia;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name="postal_info_id")
    private PostalAddress postalAddress;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "media", orphanRemoval = true)
    private List<News> news;

    protected Media() {

    }

    public Media(MediaId mediaId,
                 String name,
                 String websiteUrl,
                 String mobileWebsiteUrl,
                 SocialMedia socialMedia,
                 PostalAddress postalAddress) {
        this();

        this.setMediaId(mediaId);
        this.setWebsiteUrl(websiteUrl);
        this.setMobileWebsiteUrl(mobileWebsiteUrl);
        this.setSocialMedia(socialMedia);
        this.setPostalAddress(postalAddress);

    }

    public MediaId mediaId() {
        return mediaId;
    }

    public void setMediaId(MediaId mediaId) {
        this.mediaId = mediaId;
    }

    public String name() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String websiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        if (websiteUrl != null && !this.isValidUrl(websiteUrl)) {
            throw new IllegalArgumentException("Invalid website url");
        }
        this.websiteUrl = websiteUrl;
    }

    public String mobileWebsiteUrl() {
        return mobileWebsiteUrl;
    }

    public void setMobileWebsiteUrl(String mobileWebsiteUrl) {

        if (mobileWebsiteUrl != null && !this.isValidUrl(mobileWebsiteUrl)) {
            throw new IllegalArgumentException("Invalid mobile website url");
        }

        this.mobileWebsiteUrl = mobileWebsiteUrl;
    }

    public SocialMedia socialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(SocialMedia socialMedia) {
        this.socialMedia = socialMedia;
    }

    public PostalAddress postalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(PostalAddress postalAddress) {
        this.postalAddress = postalAddress;
    }

    private boolean isValidUrl(String url) {
        //TODO implement this later
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        //TODO implement this
        return super.equals(obj);
    }
}
