package com.example.securitydemo.service.serviceImpl;

import com.example.securitydemo.entity.User;
import com.example.securitydemo.repository.RoleRepository;
import com.example.securitydemo.repository.UserRepository;
import com.example.securitydemo.request.AuthenticationRequest;
import com.example.securitydemo.request.RegisterRequest;
import com.example.securitydemo.response.AuthenticationResponse;
import com.example.securitydemo.security.jwt.JwtService;
import com.example.securitydemo.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        var userRole = roleRepository.findByRole("User").orElse(null);


        var newUser = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .avatar(request.getAvatar())
                .roles(Collections.singleton(userRole))
                .build();
        var user = userRepository.save(newUser);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authentication(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
