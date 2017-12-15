package id.rojak.election.domain.model.election;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by imrenagi on 7/9/17.
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
