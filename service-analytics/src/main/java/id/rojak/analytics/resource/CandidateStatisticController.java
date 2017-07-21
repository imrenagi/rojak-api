package id.rojak.analytics.resource;

import id.rojak.analytics.application.statistic.CandidateStatisticApplicationService;
import id.rojak.analytics.domain.model.media.Media;
import id.rojak.analytics.domain.model.sentiments.MediaNewsCount;
import id.rojak.analytics.domain.model.sentiments.MediaSentimentGroup;
import id.rojak.analytics.resource.dto.CandidateMediaDTO;
import id.rojak.analytics.resource.dto.CandidateStatSummaryDTO;
import id.rojak.analytics.resource.dto.MediaDTO;
import id.rojak.analytics.resource.dto.MediaNewsCountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by imrenagi on 7/18/17.
 */
@RestController
public class CandidateStatisticController {

    @Autowired
    private CandidateStatisticApplicationService candidateStatisticApplicationService;

    @RequestMapping(path = "/elections/{election_id}/candidates/{candidate_id}/statistics",
            method = RequestMethod.GET)
    public ResponseEntity<String> candidateAccumulativeStatisticOvertime(
            @PathVariable("election_id") String anElectionId,
            @PathVariable("candidate_id") String aCandidateId) {
        return new ResponseEntity<String>("", HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(path = "/elections/{election_id}/candidates/{candidate_id}/medias",
            method = RequestMethod.GET)
    public ResponseEntity<CandidateMediaDTO> candidateMediaList(
            @PathVariable("election_id") String anElectionId,
            @PathVariable("candidate_id") String aCandidateId) {

        MediaSentimentGroup sentimentGroup =
                this.candidateStatisticApplicationService.mediaAbout(anElectionId, aCandidateId);

        List<MediaDTO> positives = sentimentGroup.getPositiveMedias().stream()
                .map(this.newsCountToMediaDTO()).collect(Collectors.toList());

        List<MediaDTO> negatives = sentimentGroup.getNegativeMedias().stream()
                .map(this.newsCountToMediaDTO()).collect(Collectors.toList());

        List<MediaDTO> neutrals = sentimentGroup.getNeutralMedias().stream()
                .map(this.newsCountToMediaDTO()).collect(Collectors.toList());

        CandidateMediaDTO dto = new CandidateMediaDTO(positives, negatives, neutrals);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    private Function<MediaNewsCount, MediaDTO> newsCountToMediaDTO() {
        return new Function<MediaNewsCount, MediaDTO>() {
            @Override
            public MediaDTO apply(MediaNewsCount newsCount) {
                Media media = newsCount.media();
                return new MediaDTO(media.mediaId().id(),
                        media.name(),
                        media.websiteUrl(),
                        media.websiteUrl(), //todo change with logo url
                        new MediaNewsCountDTO(newsCount.totalPositiveNews(),
                                newsCount.totalNegativeNews(),
                                newsCount.totalNeutralNews()));
            }
        };
    }

    @RequestMapping(path = "/elections/{election_id}/candidates/{candidate_id}/stat_summary",
            method = RequestMethod.GET)
    public ResponseEntity<CandidateStatSummaryDTO> candidateMediaSummaryStatistic(
            @PathVariable("election_id") String anElectionId,
            @PathVariable("candidate_id") String aCandidateId) {

        MediaSentimentGroup sentimentGroup =
                this.candidateStatisticApplicationService.mediaSentimentGroupOf(anElectionId, aCandidateId);

        CandidateStatSummaryDTO statSummary = new CandidateStatSummaryDTO(
                sentimentGroup.getPositiveMedias().size(),
                sentimentGroup.getNegativeMedias().size(),
                sentimentGroup.getNeutralMedias().size());

        return new ResponseEntity<>(statSummary, HttpStatus.OK);
    }

}
