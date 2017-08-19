package org.graviton.service;

import org.graviton.model.Account;

public interface AccountService {
    Account findByName(String name);

    Account findByNameAndPassword(String name, String password);

    void saveAccount(Account account);
}
