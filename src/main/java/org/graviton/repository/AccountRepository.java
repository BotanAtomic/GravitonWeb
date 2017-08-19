package org.graviton.repository;

import org.graviton.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("accountRepository")
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByName(String name);

    Account findByNameAndPassword(String name, String password);

}
