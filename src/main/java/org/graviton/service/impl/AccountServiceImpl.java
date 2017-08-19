package org.graviton.service.impl;

import org.graviton.model.Account;
import org.graviton.repository.AccountRepository;
import org.graviton.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account findByName(String name) {
        return accountRepository.findByName(name);
    }

    @Override public Account findByNameAndPassword(String name, String password) {
        return accountRepository.findByNameAndPassword(name, password);
    }

    @Override
    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

}
