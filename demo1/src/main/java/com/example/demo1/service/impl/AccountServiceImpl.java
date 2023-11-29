package com.example.demo1.service.impl;

import com.example.demo1.entity.Account;
import com.example.demo1.repository.AccountRepository;
import com.example.demo1.service.AccountService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void saveAccount(Account account) {
        account.setEncryptionPassword(passwordEncoder.encode(account.getEncryptionPassword()));
        accountRepository.save(account);
    }
}
