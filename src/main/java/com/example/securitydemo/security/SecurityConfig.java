package com.example.securitydemo.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity
public class SecurityConfig {

//    private final UserDetailsServiceImpl userDetailsService;
//
//    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests()
//                .requestMatchers("/*").permitAll()
//                .requestMatchers("/**").permitAll()
//                .and().formLogin().loginPage("/myapp/login")
//                .and().logout()
//                .logoutSuccessUrl("/login")
//                .invalidateHttpSession(true)
//                .deleteCookies("MY_SESSION")
//                .and().csrf().disable();
//        return http.build();
//    }
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//    @Bean
//    public AuthenticationManager authenticationManager() {
//        return authentication -> {
//            UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
//            return new UsernamePasswordAuthenticationToken(
//                    userDetails,
//                    authentication.getCredentials(),
//                    userDetails.getAuthorities()
//            );
//        };
//    }


}


