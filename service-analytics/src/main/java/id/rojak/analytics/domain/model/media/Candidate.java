package id.rojak.analytics.domain.model.media;

import id.rojak.analytics.common.domain.model.IdentifiedValueObject;
import id.rojak.analytics.domain.model.candidate.CandidateId;
import id.rojak.analytics.domain.model.election.ElectionId;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by imrenagi on 7/16/17.
 */
@Entity
@Table(name = "tbl_candidate_of_media")
public class Candidate extends IdentifiedValueObject {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "media_id_id", referencedColumnName = "id")
    private Media media;

    @Embedded
    private MediaId mediaId;

    @Embedded
    private ElectionId electionId;

    @Embedded
    private CandidateId candidateId;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    protected Candidate() {
    }

    public Candidate(CandidateId candidateId,
                     MediaId mediaId,
                     ElectionId electionId) {

        this();

        this.setCandidateId(candidateId);
        this.setMediaId(mediaId);
        this.setElectionId(electionId);

    }

    public Media media() {
        return this.media;
    }

    public void setMedia(Media media) {
        this.assertArgumentNotNull(media, "Media should not be null");

        this.media = media;
    }

    public MediaId mediaId() {
        return this.mediaId;
    }

    public void setMediaId(MediaId mediaId) {
        this.assertArgumentNotNull(mediaId, "Media Id is required");

        this.mediaId = mediaId;
    }

    public ElectionId electionId() {
        return this.electionId;
    }

    public void setElectionId(ElectionId electionId) {
        this.assertArgumentNotNull(electionId, "Election Id is required");

        this.electionId = electionId;
    }

    public CandidateId candidateId() {
        return this.candidateId;
    }

    public void setCandidateId(CandidateId candidateId) {
        this.assertArgumentNotNull(candidateId, "Candidate Id is required");

        this.candidateId = candidateId;
    }
}
