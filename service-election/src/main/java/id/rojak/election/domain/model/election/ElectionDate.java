package id.rojak.election.domain.model.election;

import id.rojak.election.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Date;

/**
 * Created by inagi on 7/4/17.
 */
//@Embeddable
public class ElectionDate extends ValueObject {

//    @Column(name="election_date")
    private Date electionDate;

//    @Column(name="election_campaign_start_date")
    private Date campaignStartDate;

//    @Column(name="election_campaign_end_date")
    private Date campaignEndDate;

    private ElectionDate() {
        super();
    }

    public ElectionDate(Date election,
                        Date campaignStart,
                        Date campaignEnd) {
        this();
        if (campaignEnd.before(campaignStart)) {
            throw new IllegalArgumentException("Campaign end date must be after campaign startDate");
        }
        if (election.before(campaignEnd) || election.before(campaignStart)) {
            throw new IllegalArgumentException("ElectionDTO date must be after campaign start and end date");
        }

        this.setElectionDate(election);
        this.setCampaignStartDate(campaignStart);
        this.setCampaignEndDate(campaignEnd);
    }

    public Date electionDate() {
        return this.electionDate;
    }

    public Date campaignStart() {
        return this.campaignStartDate;
    }

    public Date campaignEnd() {
        return this.campaignEndDate;
    }

    public void setElectionDate(Date electionDate) {
        this.assertArgumentNotNull(electionDate, "ElectionDTO Date must not be empty!");

        this.electionDate = electionDate;
    }

    public void setCampaignStartDate(Date campaignStartDate) {
        this.assertArgumentNotNull(electionDate, "Campaign Start Date must not be empty!");

        this.campaignStartDate = campaignStartDate;
    }

    public void setCampaignEndDate(Date campaignEndDate) {
        this.assertArgumentNotNull(electionDate, "Campaign End Date must not be empty!");

        this.campaignEndDate = campaignEndDate;
    }
}
