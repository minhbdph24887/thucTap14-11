package com.example.thuctap.postgresql.service;

import com.example.thuctap.postgresql.entity.Account;
import com.example.thuctap.postgresql.entity.UserDetailCustom;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface AccountService {
    List<Account> getAccount();

    Account addAccount(Account account);

    Account detailAccount(Long idAccount);

    Account updateAccount(Account account);

    void deleteAccount(Long idAccount);

    Account detailAccountByUserName(String username);

    UserDetailCustom loadCustomsAccountByIdAccount(Long idAccount);
}
