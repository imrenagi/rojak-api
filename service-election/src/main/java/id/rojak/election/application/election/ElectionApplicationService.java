package id.rojak.election.application.election;

import id.rojak.election.common.error.ResourceNotFoundException;
import id.rojak.election.domain.model.election.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by inagi on 7/4/17.
 */
@Service
public class ElectionApplicationService implements IElectionApplicationService {

    private static Logger log = LoggerFactory.getLogger(ElectionApplicationService.class);

    @Autowired
    private ElectionRepository electionRepository;

    @Autowired
    private CityRepository cityRepository;

    @Override
    @Transactional
    public Page<Election> allElections(Pageable pageRequest) {
        Page<Election> elections = electionRepository().findAll(pageRequest);

        return elections;
    }

    @Override
    @Transactional
    public Election election(String anElectionId) {

        Election election = electionRepository()
                .findByElectionId(new ElectionId(anElectionId));

        if (election == null) {
            throw new ResourceNotFoundException("Election " + anElectionId +
                    " doesn't exist");
        }

        return election;
    }

    @Override
    @Transactional
    public Election newElection(NewElectionCommand aCommand) {

        City city = cityRepository().findOne(aCommand.getCityId());

        if (city == null) {
            throw new ResourceNotFoundException(
                    String.format("City id %d is not found",
                            aCommand.getCityId()));
        }

        ElectionType electionType = ElectionType.valueOf(aCommand.getElectionType());

        Election election = new Election(
                new ElectionId(this.electionRepository().nextId()),
                aCommand.getName(),
                new Date(aCommand.getElectionDate()),
                new Date(aCommand.getCampaignStartDate()),
                new Date(aCommand.getCampaignEndDate()),
                city,
                electionType);

        log.info("New Election with id {} ", election.electionId().id());

        election = this.electionRepository().save(election);

        return election;
    }

    @Override
    @Transactional
    public void changeElectionDetail(ElectionDetailChangeCommand aCommand) {

        City city = cityRepository().findOne(aCommand.getCityId());

        if (city == null) {
            throw new ResourceNotFoundException(
                    String.format("City id %d is not found",
                            aCommand.getCityId()));
        }

        ElectionType electionType = ElectionType.valueOf(aCommand.getElectionType());

        Election election = electionRepository()
                .findByElectionId(new ElectionId(aCommand.getElectionId()));

        if (election == null) {
            throw new ResourceNotFoundException(
                    String.format("Election %s is not found", aCommand.getElectionId()));
        }

        election.setName(aCommand.getName());
        election.setCity(city);
        election.setType(electionType);
        election.setElectionDate(
                new ElectionDate(
                        new Date(aCommand.getElectionDate()),
                        new Date(aCommand.getCampaignStartDate()),
                        new Date(aCommand.getCampaignEndDate())));

        this.electionRepository().save(election);
    }

    public ElectionRepository electionRepository() {
        return this.electionRepository;
    }

    public CityRepository cityRepository() {
        return this.cityRepository;
    }

}
