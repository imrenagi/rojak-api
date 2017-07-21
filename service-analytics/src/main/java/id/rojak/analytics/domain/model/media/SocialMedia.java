package id.rojak.analytics.domain.model.media;

import id.rojak.analytics.common.domain.model.IdentifiedValueObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by imrenagi on 7/14/17.
 */
@Entity
@Table(name="tbl_social_media")
public class SocialMedia extends IdentifiedValueObject {

    @Column(name="facebook_url")
    private String facebookUrl;

    @Column(name="twitter_id")
    private String twitterId;

    @Column(name="instagram_id")
    private String instagramId;

    protected SocialMedia() {
    }

    public SocialMedia(String facebookUrl,
                       String twitterId,
                       String instagramId) {
        this();

        this.setFacebookUrl(facebookUrl);
        this.setTwitterId(twitterId);
        this.setInstagramId(instagramId);
    }

    public String facebookUrl() {
        return facebookUrl;
    }

    public String twitterId() {
        return twitterId;
    }

    public String instagramId() {
        return instagramId;
    }

    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }

    public void setTwitterId(String twitterId) {
        this.twitterId = twitterId;
    }

    public void setInstagramId(String instagramId) {
        this.instagramId = instagramId;
    }

    @Override
    public boolean equals(Object obj) {
        //TODO implement this
        return super.equals(obj);
    }
}
