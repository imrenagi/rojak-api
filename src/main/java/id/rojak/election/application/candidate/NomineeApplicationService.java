package id.rojak.election.application.candidate;

import id.rojak.election.application.election.ElectionApplicationService;
import id.rojak.election.domain.model.candidate.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by imrenagi on 7/9/17.
 */
@Service
public class NomineeApplicationService { //implements INomineeApplicationService {

    private static Logger log = LoggerFactory.getLogger(ElectionApplicationService.class);

    @Autowired
    private NomineeRepository nomineeRepository;

    @Transactional
    public Page<Nominee> allNominees(Pageable pageRequest) {
        Page<Nominee> nominees = this.nomineeRepository.findAll(pageRequest);

        return nominees;
    }

    @Transactional
    public Nominee newNominee(NewNomineeCommand aCommand) {

        Nominee nominee =
                this.nomineeRepository
                        .findByNomineeId(new NomineeId(aCommand.getId()));

        if (nominee != null) {
            throw new IllegalArgumentException(
                    String.format("Nominee with id %s is exist", aCommand.getId()));
        }

        Nominee newNominee = new Nominee(
                new NomineeId(aCommand.getId()),
                new FullName(
                        aCommand.getFirstName(),
                        aCommand.getMiddleName(),
                        aCommand.getLastName()),
                aCommand.getNickName(),
                aCommand.getImageUrl(),
                new SocialMediaInformation(
                        aCommand.getWebUrl(),
                        aCommand.getTwitterId(),
                        aCommand.getInstagramId(),
                        aCommand.getFacebookUrl()));

        newNominee = this.nomineeRepository.save(newNominee);

        return newNominee;
    }

    @Transactional
    public Nominee updateNominee(String aNomineeId, UpdateNomineeCommand aCommand) {

        Nominee nominee =
                this.nomineeRepository
                        .findByNomineeId(new NomineeId(aNomineeId));

        if (nominee == null) {
            throw new IllegalArgumentException(
                    String.format("Nominee id %s is not exist", aNomineeId));
        }

        System.out.println(aCommand.getFirstName());

        nominee.setFullName(new FullName(aCommand.getFirstName(),
                            aCommand.getMiddleName(),
                            aCommand.getLastName()));
        nominee.setNickName(aCommand.getNickName());
        nominee.installPhoto(aCommand.getImageUrl());
        nominee.setSocialMediaInformation(new SocialMediaInformation(
                aCommand.getWebUrl(),
                aCommand.getTwitterId(),
                aCommand.getInstagramId(),
                aCommand.getFacebookUrl()));

        nominee = this.nomineeRepository.save(nominee);

        return nominee;
    }

    @Transactional
    public void removeNominee(String aNomineeId) {

        Nominee nominee =
                this.nomineeRepository
                        .findByNomineeId(new NomineeId(aNomineeId));

        if (nominee == null) {
            throw new IllegalArgumentException(
                    String.format("Nominee id %s is not exist", aNomineeId));
        }

        this.nomineeRepository.delete(nominee);
    }
}
