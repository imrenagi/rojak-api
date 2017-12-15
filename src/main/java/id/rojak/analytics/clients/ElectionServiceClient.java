package id.rojak.analytics.clients;

import id.rojak.analytics.resource.dto.CandidateDTO;
import id.rojak.analytics.resource.dto.ElectionDTO;
import org.springframework.stereotype.Component;

/**
 * Created by inagi on 7/28/17.
 */
public interface ElectionServiceClient {
    ElectionDTO election(String anElectionId);

    CandidateDTO candidate(String anElectionId, String candidateId);
}

