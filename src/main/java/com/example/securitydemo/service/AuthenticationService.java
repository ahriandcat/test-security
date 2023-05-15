package com.example.securitydemo.service;

import com.example.securitydemo.request.AuthenticationRequest;
import com.example.securitydemo.request.RegisterRequest;
import com.example.securitydemo.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authentication(AuthenticationRequest request);
}
