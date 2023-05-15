package com.example.securitydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/myapp")
public class LoginController {
    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
