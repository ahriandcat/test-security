package com.example.securitydemo.controller;

import com.example.securitydemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor

public class AdminController {
    private final UserService userService;

    @GetMapping("getUsers")
    public ResponseEntity<Object> getListUsers(){
        return ResponseEntity.ok(userService.findAll());
    }
}
