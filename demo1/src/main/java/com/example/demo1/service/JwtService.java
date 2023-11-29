package com.example.demo1.service;

import com.example.demo1.entity.Account;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

public interface JwtService {
    String generateToken(Account account, Collection<SimpleGrantedAuthority> authorities);

    String generateRefreshToken(Account account, Collection<SimpleGrantedAuthority> authorities);
}
