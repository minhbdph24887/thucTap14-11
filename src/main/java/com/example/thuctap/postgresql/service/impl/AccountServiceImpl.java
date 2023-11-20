package com.example.thuctap.postgresql.service.impl;

import com.example.thuctap.postgresql.entity.Account;
import com.example.thuctap.postgresql.repository.AccountRepository;
import com.example.thuctap.postgresql.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public List<Account> getAccount() {
        return accountRepository.findAll();
    }

    @Override
    public Account addAccount(Account account) {
        Account newAccount = Account.builder()
                .codeAccount(account.getCodeAccount())
                .nameAccount(account.getNameAccount())
                .birthday(account.getBirthday())
                .gender(account.isGender())
                .address(account.getAddress())
                .password(account.getPassword())
                .status(account.getStatus())
                .role(account.getRole())
                .build();
        return accountRepository.save(newAccount);
    }

    @Override
    public Account detailAccount(Long idAccount) {
        return accountRepository.findById(idAccount).orElse(null);
    }

    @Override
    public Account updateAccount(Account account) {
        Account updateAccount = accountRepository.findById(account.getIdAccount()).orElse(null).builder()
                .idAccount(account.getIdAccount())
                .codeAccount(account.getCodeAccount())
                .nameAccount(account.getNameAccount())
                .birthday(account.getBirthday())
                .gender(account.isGender())
                .address(account.getAddress())
                .password(account.getPassword())
                .status(account.getStatus())
                .role(account.getRole())
                .build();
        return accountRepository.save(updateAccount);
    }

    @Override
    public void deleteAccount(Long idAccount) {
        accountRepository.deleteById(idAccount);
    }

    @Override
    public Account detailAccountByUserNameAndPassword(String username) {
        return accountRepository.detailAccountByUsernameAndPassword(username);
    }
}
