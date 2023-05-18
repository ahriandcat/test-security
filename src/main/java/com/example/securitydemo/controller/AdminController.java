package com.example.securitydemo.controller;

import com.example.securitydemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin")
@RequiredArgsConstructor
//@PreAuthorize("hasRole('Admin')")
public class AdminController {
    private final UserService userService;

    @GetMapping("getUsers")
    public ResponseEntity<Object> getListUsers(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("=======================================");
        System.out.println(authentication);
        return ResponseEntity.ok(userService.findAll());
    }
}
