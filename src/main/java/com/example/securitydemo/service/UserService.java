package com.example.securitydemo.service;

import com.example.securitydemo.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    Optional<User> findById(Long id);

    void save(User user);
}
