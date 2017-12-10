package id.rojak.election.domain.model.candidate;


import id.rojak.common.AssertionConcern;
import org.apache.commons.validator.routines.UrlValidator;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by imrenagi on 7/6/17.
 */
@Embeddable
public class SocialMediaInformation extends AssertionConcern implements Serializable {

    @Column(name="social_media_web_url")
    private String webUrl;
    @Column(name="social_media_twitter_id")
    private String twitterId;
    @Column(name="social_media_instagram_id")
    private String instagramId;
    @Column(name="social_media_facebook_url")
    private String facebookUrl;

    protected SocialMediaInformation() {
        super();
    }

    public SocialMediaInformation(String aWebUrl,
                                  String aTwitterId,
                                  String anInstagramId,
                                  String aFacebookUrl) {
        this();

        this.setWebUrl(aWebUrl);
        this.setTwitterId(aTwitterId);
        this.setInstagramId(anInstagramId);
        this.setFacebookUrl(aFacebookUrl);
    }

    @Override
    public boolean equals(Object anObject) {
        boolean equalObjects = false;

        if (anObject != null && this.getClass() == anObject.getClass()) {
            SocialMediaInformation typedObject = (SocialMediaInformation) anObject;
            equalObjects =
                    this.facebookUrl().equals(typedObject.facebookUrl()) &&
                            this.instagramId().equals(typedObject.instagramId()) &&
                            this.twitterId().equals(typedObject.twitterId()) &&
                            this.webUrl().equals(typedObject.webUrl());
        }

        return equalObjects;
    }

    public String webUrl() {
        return this.webUrl;
    }

    public void setWebUrl(String webUrl) {
        if (!webUrl.isEmpty())
            this.assertArgumentTrue(this.isValidUrl(webUrl), "Web URL must be valid format");

        this.webUrl = webUrl;
    }

    private boolean isValidUrl(String url) {
        String[] schemes = {"http","https"};
        UrlValidator urlValidator = new UrlValidator(schemes);

        return urlValidator.isValid(url);
    }

    public String twitterId() {
        return this.twitterId;
    }

    public void setTwitterId(String twitterId) {
        if (!twitterId.isEmpty()) {
            this.assertArgumentLength(twitterId, 3, 50, "Twitter Id length must be between 3 and 50");
        }

        this.twitterId = twitterId;
    }

    public String instagramId() {
        return this.instagramId;
    }

    public void setInstagramId(String instagramId) {
        if (!instagramId.isEmpty()) {
            this.assertArgumentLength(instagramId, 3, 50, "Instagram Id length must be between 3 and 50");
        }
        this.instagramId = instagramId;
    }

    public String facebookUrl() {
        return facebookUrl;
    }

    public void setFacebookUrl(String facebookUrl) {
        if (!facebookUrl.isEmpty()) {
            this.assertArgumentTrue(this.isValidUrl(facebookUrl), "Facebook Url must be a valid Url");
        }

        this.facebookUrl = facebookUrl;
    }
}
