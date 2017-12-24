package id.rojak.analytics.application.media;

import id.rojak.analytics.domain.model.election.ElectionId;
import id.rojak.analytics.domain.model.media.*;
import id.rojak.common.error.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by imrenagi on 7/18/17.
 */
@Service
public class MediaApplicationService {

    @Autowired
    private MediaRepository mediaRepository;

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
        Media media = this.mediaRepository.findByMediaId(new MediaId(mediaId));

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

        SocialMedia socialMedia = new SocialMedia(
                    aCommand.getFacebookUrl(),
                    aCommand.getTwitterId(),
                    aCommand.getInstagramId());

        Media newMedia = new Media(new MediaId(aCommand.getId()),
                aCommand.getName(),
                aCommand.getWebUrl(),
                aCommand.getMobileWebUrl(),
                socialMedia,
                null);

        return this.mediaRepository.save(newMedia);
    }

    @Transactional
    public Media updateMedia(String aMediaId, UpdateMediaCommand aCommand) {

        Media media = this.mediaRepository.findByMediaId(
                new MediaId(aMediaId));

        if (media == null) {
            throw new ResourceNotFoundException("Media doesn't exist");
        }

        SocialMedia socialMedia = new SocialMedia(
                aCommand.getFacebookUrl(),
                aCommand.getTwitterId(),
                aCommand.getInstagramId());

        media.setName(aCommand.getName());
        media.setWebsiteUrl(aCommand.getWebUrl());
        media.setMobileWebsiteUrl(aCommand.getMobileWebUrl());
        media.setSocialMedia(socialMedia);

        return this.mediaRepository.save(media);
    }

    public Long numberOfArticles(String electionId, String mediaId) {
        return 100L;
    }

    @Transactional
    public void remove(String aMediaId) {

        Media media = this.mediaRepository.findByMediaId(
                new MediaId(aMediaId));

        if (media == null) {
            throw new ResourceNotFoundException("Media doesn't exist");
        }

        this.mediaRepository.delete(media);
    }
}


