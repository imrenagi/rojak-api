package id.rojak.election.application.candidate;

/**
 * Created by imrenagi on 7/8/17.
 */
public class RemoveCandidateCommand {

    private String electionId;
    private String candidateId;

    public RemoveCandidateCommand(String electionId, String candidateId) {
        this.electionId = electionId;
        this.candidateId = candidateId;
    }

    public String getElectionId() {
        return electionId;
    }

    public void setElectionId(String electionId) {
        this.electionId = electionId;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }
}
