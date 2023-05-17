package com.example.securitydemo.security;

import javax.annotation.PostConstruct;

import com.example.securitydemo.entity.Role;
import com.example.securitydemo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultRoleInitializer {

    private final RoleRepository roleRepository;

    @Autowired
    public DefaultRoleInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
        Role defaultRoleAdmin = roleRepository.findById(1).orElse(null);
        Role defaultRoleUser = roleRepository.findById(2).orElse(null);

        if (defaultRoleAdmin == null) {
            defaultRoleAdmin = new Role();
            defaultRoleAdmin.setId(1);
            defaultRoleAdmin.setRole("Admin");
            roleRepository.save(defaultRoleAdmin);
        }

        if (defaultRoleUser == null) {
            defaultRoleUser = new Role();
            defaultRoleUser.setId(2);
            defaultRoleUser.setRole("User");
            roleRepository.save(defaultRoleUser);
        }
    }
}
