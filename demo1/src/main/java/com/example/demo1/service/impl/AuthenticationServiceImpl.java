package com.example.demo1.service.impl;

import com.example.demo1.auth.AuthenticationRequest;
import com.example.demo1.auth.AuthenticationResponse;
import com.example.demo1.entity.Account;
import com.example.demo1.entity.Role;
import com.example.demo1.repository.AccountRepository;
import com.example.demo1.repository.RoleRepository;
import com.example.demo1.service.AuthenticationService;
import com.example.demo1.service.JwtService;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;

    public AuthenticationServiceImpl(AccountRepository accountRepository,
                                     RoleRepository roleRepository,
                                     AuthenticationManager authenticationManager,
                                     JwtService jwtService) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    @Override
    public void addAuthentication(String email, String nameRole) {
        Account account = accountRepository.findAccountByEmail(email).orElse(null);
        Role role = roleRepository.findRoleByName(nameRole);
        assert account != null;
        account.getRoles().add(role);
    }

    @Override
    public AuthenticationResponse authentications(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        Account account = accountRepository.findAccountByEmail(authenticationRequest.getEmail()).orElseThrow();
        List<Role> itemsRole = null;
        itemsRole = roleRepository.getAllRoleByEmail(account.getEmail());

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        itemsRole.forEach(i -> authorities.add(new SimpleGrantedAuthority(i.getName())));

        var jwtToken = jwtService.generateToken(account, authorities);
        var jwtRefreshToken = jwtService.generateRefreshToken(account, authorities);
        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken(jwtToken);
        response.setRefreshToken(jwtRefreshToken);
        return response;
    }
}
