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
        Role defaultRole = roleRepository.findById(1).orElse(null);

        if (defaultRole == null) {
            defaultRole = new Role();
            defaultRole.setId(1);
            defaultRole.setRole("User");
            roleRepository.save(defaultRole);
        }
    }
}
