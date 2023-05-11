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

    @Test
    void testCreateUser() {
        Role role = new Role();
        role.setRole("Employee");
        roleRepository.save(role);


        User user = new User();
        user.setName("john");
        user.setEmail("john@gmail.com");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);

        List<User> users = userRepository.findAll();
        assertThat(users).hasSize(2);
    }

    @Test
    void select() {
        List<User> l = userRepository.findAll();
        System.out.println("======================================");
        l.forEach(i -> System.out.println(i.getName()));
        System.out.println("======================================");
        assertThat(l).hasSize(1);
    }

}
