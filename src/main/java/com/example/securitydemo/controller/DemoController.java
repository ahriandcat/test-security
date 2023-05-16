package com.example.securitydemo.controller;


import com.example.securitydemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
@RequiredArgsConstructor
public class DemoController {

    private final UserService userService;
    @GetMapping
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("hello");
    }

    @GetMapping("getUsers")
    public ResponseEntity<Object> getListUsers(){
        return ResponseEntity.ok(userService.findAll());
    }
}
