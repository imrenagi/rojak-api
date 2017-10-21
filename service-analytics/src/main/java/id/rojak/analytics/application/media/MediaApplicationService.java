package id.rojak.analytics.application.media;

import id.rojak.analytics.common.error.ResourceNotFoundException;
import id.rojak.analytics.domain.model.election.ElectionId;
import id.rojak.analytics.domain.model.media.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by imrenagi on 7/18/17.
 */
@Service
public class MediaApplicationService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private MediaCacheRepository mediaCacheRepository;

    @Transactional
    public Page<Media> allMedias(ElectionId electionId, Pageable pageRequest) {
        return null;
    }

    @Transactional
    public Page<Media> allMedias(Pageable pageRequest) {
        Page<Media> medias = this.mediaRepository.findAll(pageRequest);

        return medias;
    }

    @Transactional
    public Media media(String mediaId) {
        Media media = this.mediaCacheRepository.findMedia(new MediaId(mediaId));

        if (media == null) {
            log.info("Media doesn't exists in cache. Retrieve it from db!");
            media = this.mediaRepository.findByMediaId(new MediaId(mediaId));

            if (media != null)
                this.mediaCacheRepository.saveMedia(media);
            
        } else {
            log.info("Media does exists in cache. Don't retrieved it from db!");
        }

        if (media == null) {
            throw new ResourceNotFoundException("Media is not found!");
        }

        return media;
    }

    @Transactional
    public Media newMedia(NewMediaCommand aCommand) {

        Media media = this.mediaRepository.findByMediaId(
                new MediaId(aCommand.getId()));

        if (media != null) {
            throw new IllegalArgumentException("Media with the same id exists!");
        }

        SocialMedia socialMedia = null;
        if (aCommand.getSocialMedia() != null) {
            socialMedia = new SocialMedia(aCommand.getSocialMedia().getFacebookUrl(),
                    aCommand.getSocialMedia().getTwitterId(),
                    aCommand.getSocialMedia().getInstagramId());
        }

        PostalAddress postalAddress = null;
        if (aCommand.getPostalAddress() != null) {
            postalAddress = new PostalAddress(aCommand.getPostalAddress().getStreetAddress(),
                    aCommand.getPostalAddress().getCountry(),
                    aCommand.getPostalAddress().getProvince(),
                    aCommand.getPostalAddress().getCity(),
                    aCommand.getPostalAddress().getZipCode());
        }

        Media newMedia = new Media(new MediaId(aCommand.getId()),
                aCommand.getName(),
                aCommand.getWebUrl(),
                aCommand.getMobileWebUrl(),
                socialMedia,
                postalAddress);

        return this.mediaRepository.save(newMedia);
    }

    public Long numberOfArticles(String electionId, String mediaId) {
        return 100L;
    }


}


