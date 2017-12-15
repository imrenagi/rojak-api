package id.rojak.analytics.application.election;

import id.rojak.analytics.clients.ElectionServiceClient;
import id.rojak.analytics.clients.ElectionServiceClientImpl;
import id.rojak.analytics.resource.dto.CandidateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by imrenagi on 9/29/17.
 */
@Service
public class ElectionAppService {

    @Autowired
//    private ElectionServiceClient electionServiceClient;
    private ElectionServiceClientImpl electionServiceClient;

    public CandidateDTO candidate(String electionId,
                                  String candidateId) {

        //TODO implement cache for candidate here!
        return electionServiceClient.candidate(electionId, candidateId);
    }


}
