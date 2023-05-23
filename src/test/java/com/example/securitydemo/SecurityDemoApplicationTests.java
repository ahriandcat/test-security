package com.example.securitydemo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.securitydemo.entity.Role;
import com.example.securitydemo.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.securitydemo.entity.User;
import com.example.securitydemo.repository.UserRepository;



@SpringBootTest
class SecurityDemoApplicationTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Test
    void contextLoads() {
    }


}
