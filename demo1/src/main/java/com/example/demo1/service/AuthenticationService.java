package com.example.demo1.service;

import com.example.demo1.auth.AuthenticationRequest;
import com.example.demo1.auth.AuthenticationResponse;

public interface AuthenticationService {
    void addAuthentication(String email, String nameRole);

    AuthenticationResponse authentications(AuthenticationRequest authenticationRequest);
}
