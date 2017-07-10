package id.rojak.election.application.election;

import com.netflix.discovery.converters.Auto;
import id.rojak.election.domain.model.candidate.*;
import id.rojak.election.domain.model.election.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by inagi on 7/4/17.
 */
@Service
public class ElectionApplicationService {

    private static Logger log = LoggerFactory.getLogger(ElectionApplicationService.class);

    @Autowired
    private ElectionRepository electionRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private CityRepository cityRepository;

    @Transactional
    public List<Election> allElections() {
        Country c = countryRepository.findOne(1L);
        log.info("Country is {}-{} has {} provinces", c.name(), c.code(), c.provinces().size());


        Province p = provinceRepository.findOne(1L);
        log.info("Province {} has {} cities and the country is {}", p.name(), p.cities().size(), p.country().name());

        City city = cityRepository.findOne(1L);
        log.info("City {} is a part of {} province", city.name(), city.province().name());

        List<Election> elections = electionRepository().findAll();

        return elections;
    }

    public ElectionRepository electionRepository() {
        return this.electionRepository;
    }

}
