package com.example.securitydemo.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

public class DemoOAuth2User implements OAuth2User {
    private final OAuth2User oauth2User;

    public DemoOAuth2User(OAuth2User oauth2User) {
        this.oauth2User = oauth2User;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oauth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oauth2User.getAuthorities();
    }

    @Override
    public String getName() {
//        System.out.println(oauth2User.<String>getAttribute("email"));
        return oauth2User.getAttribute("email");
    }
}
