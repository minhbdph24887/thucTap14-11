package com.example.thuctap.postgresql.service.impl;

import com.example.thuctap.postgresql.entity.Account;
import com.example.thuctap.postgresql.entity.UserDetailCustom;
import com.example.thuctap.postgresql.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> account = accountRepository.findAccountByUserName(username);
        return new UserDetailCustom(account.get());
    }
}
