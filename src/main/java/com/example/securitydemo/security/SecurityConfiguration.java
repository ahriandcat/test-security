package com.example.securitydemo.security;

import com.example.securitydemo.dto.DemoOAuth2User;
import com.example.securitydemo.security.jwt.JwtAuthenticationFilter;
import com.example.securitydemo.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.DefaultRedirectStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.IOException;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;
    private final UserService userService;

    @Bean
    public DefaultRedirectStrategy redirectStrategy() {
        return new DefaultRedirectStrategy();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .cors()
                .configurationSource(corsConfigurationSource())
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/**")
                .permitAll()
                .requestMatchers("/api/v1/auth/logout")
                .authenticated()
                .requestMatchers("/api/v1/user/**")
                .hasAnyAuthority("Admin","User")
                .requestMatchers("/api/v1/admin/**")
                .hasAuthority("Admin")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider)
                .logout()
                .logoutUrl("/api/v1/auth/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                .and()
                .oauth2Login()
                .defaultSuccessUrl("/api/v1/auth/oauth2")
                .userInfoEndpoint()
                .userService(oAuth2UserService())
                .and().successHandler((request, response, authentication) -> {
                    DemoOAuth2User oauthUser = (DemoOAuth2User) authentication.getPrincipal();
                    System.out.println("***************");
                    System.out.println(oauthUser.getEmail());
                    System.out.println(oauthUser.getName());
                    System.out.println(oauthUser.getAttributes());
                    System.out.println(oauthUser.getAuthorities());
                    System.out.println("***************");
//                    userService.processOAuthPostLogin(oauthUser.getEmail());
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.sendRedirect("/api/v1/auth/oauth2");
                });
        return httpSecurity.build();
    }

    @Bean
    public OAuth2UserService oAuth2UserService() {
        return new OAuth2UserService();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.setAllowCredentials(false);
        corsConfiguration.setMaxAge(3600L);
        corsConfiguration.addExposedHeader("Authorization");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}
