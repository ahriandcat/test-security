package com.example.securitydemo.controller;

import com.example.securitydemo.entity.User;
import com.example.securitydemo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("user", userService.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid user Id:" + id)));
        return "userDetails";
    }

    @GetMapping("/new")
    public String newUserForm(Model model) {
        model.addAttribute("user", new User());
        return "userForm";
    }

    @PostMapping("")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/users";
    }
}

