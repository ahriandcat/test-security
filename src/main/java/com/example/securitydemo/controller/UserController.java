package com.example.securitydemo.controller;

import com.example.securitydemo.dto.UserInfoDTO;
import com.example.securitydemo.entity.User;
import com.example.securitydemo.exception.NotFoundException;
import com.example.securitydemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
//@PreAuthorize("hasAnyRole('Admin','User')")
public class UserController {

    private final UserService userService;
    @GetMapping("detail")
    public ResponseEntity<UserInfoDTO> getUserInfo(Authentication authentication) {
        String username = authentication.getName();
        Optional<User> user = userService.findByEmail(username);
        if (user.isPresent()) {
            User userDetails = user.get();
            UserInfoDTO userInfo = new UserInfoDTO(userDetails.getId(), userDetails.getFirstname(), userDetails.getLastname(), userDetails.getEmail());
            return ResponseEntity.ok(userInfo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
