package id.rojak.election.application.election;


import id.rojak.common.error.ResourceNotFoundException;
import id.rojak.election.domain.model.election.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by inagi on 7/4/17.
 */
@Service
public class ElectionApplicationService {

    private static Logger log = LoggerFactory.getLogger(ElectionApplicationService.class);

    @Autowired
    private ElectionRepository electionRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Transactional
    public Page<Election> allElections(Pageable pageRequest) {
        Page<Election> elections = electionRepository().findAll(pageRequest);

        return elections;
    }

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

    @Transactional
    public Election newElection(NewElectionCommand aCommand) {

        Province province = provinceRepository().findOne(aCommand.getProvinceId());

        if (province == null) {
            throw new ResourceNotFoundException(
                    String.format("Province id %d is not found",
                            aCommand.getProvinceId()));
        }

        City city = cityRepository().findOne(aCommand.getCityId());

        if (city == null) {
            throw new ResourceNotFoundException(
                    String.format("City id %d is not found",
                            aCommand.getCityId()));
        }

        ElectionType electionType = ElectionType.valueOf(aCommand.getElectionType());

        Election election = electionRepository()
                .findByElectionId(new ElectionId(aCommand.getId()));

        if (election != null) {
            throw new ResourceNotFoundException(
                    String.format("Election %s does exists", aCommand.getId()));
        }

        election = new Election(
                new ElectionId(aCommand.getId()),
                aCommand.getName(),
                new Date(aCommand.getElectionDate()),
                new Date(aCommand.getCampaignStartDate()),
                new Date(aCommand.getCampaignEndDate()),
                province,
                city,
                electionType);

        log.info("New Election with id {} ", election.electionId().id());

        election = this.electionRepository().save(election);

        return election;
    }

    @Transactional
    public void changeElectionDetail(String anElectionId, ElectionDetailChangeCommand aCommand) {

        Province province = provinceRepository().findOne(aCommand.getProvinceId());

        if (province == null) {
            throw new ResourceNotFoundException(
                    String.format("Province id %d is not found",
                            aCommand.getProvinceId()));
        }

        City city = cityRepository().findOne(aCommand.getCityId());

        if (city == null) {
            throw new ResourceNotFoundException(
                    String.format("City id %d is not found",
                            aCommand.getCityId()));
        }

        ElectionType electionType = ElectionType.valueOf(aCommand.getElectionType());

        Election election = electionRepository()
                .findByElectionId(new ElectionId(anElectionId));

        if (election == null) {
            throw new ResourceNotFoundException(
                    String.format("Election %s is not found", anElectionId));
        }

        election.setName(aCommand.getName());
        election.setCity(city);
        election.setProvince(province);
        election.setType(electionType);
        election.setElectionDate(
                new ElectionDate(
                        new Date(aCommand.getElectionDate()),
                        new Date(aCommand.getCampaignStartDate()),
                        new Date(aCommand.getCampaignEndDate())));

        this.electionRepository().save(election);
    }

    @Transactional
    public void removeElection(String anElectionId) {

        Election election = electionRepository()
                .findByElectionId(new ElectionId(anElectionId));

        if (election == null) {
            throw new ResourceNotFoundException(
                    String.format("Election %s is not found", anElectionId));
        }

        this.electionRepository().delete(election);
    }

    public ElectionRepository electionRepository() {
        return this.electionRepository;
    }

    public CityRepository cityRepository() {
        return this.cityRepository;
    }

    public ProvinceRepository provinceRepository() {
        return this.provinceRepository;
    }

}
