package id.rojak.account.repositories;

import id.rojak.account.domains.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by imrenagi on 5/7/17.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByEmail(String email);

    Account findByUsername(String username);
}
