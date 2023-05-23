package com.example.securitydemo.security;

import com.example.securitydemo.dto.AccessTokenResponse;
import com.example.securitydemo.dto.GoogleUserInfo;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GoogleAuthService {

    private final RestTemplate restTemplate;

    public GoogleAuthService() {
        this.restTemplate = new RestTemplate();
    }

    public String buildAuthorizationUrl(String clientId, String redirectUri, String scope) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://accounts.google.com/o/oauth2/auth")
                .queryParam("client_id", clientId)
                .queryParam("redirect_uri", redirectUri)
                .queryParam("scope", scope)
                .queryParam("response_type", "code");

        return builder.toUriString();
    }

    public String getAccessToken(String clientId, String clientSecret, String redirectUri, String code) {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("code", code);
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("redirect_uri", redirectUri);
        body.add("grant_type", "authorization_code");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<AccessTokenResponse> responseEntity = restTemplate.postForEntity(
                "https://oauth2.googleapis.com/token",
                request,
                AccessTokenResponse.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            AccessTokenResponse accessTokenResponse = responseEntity.getBody();
            if (accessTokenResponse != null) {
                return accessTokenResponse.getAccessToken();
            }
        }

        throw new IllegalStateException("Failed to retrieve access token");
    }

    public GoogleUserInfo getUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<GoogleUserInfo> responseEntity = restTemplate.exchange(
                "https://www.googleapis.com/oauth2/v3/userinfo",
                HttpMethod.GET,
                requestEntity,
                GoogleUserInfo.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        }

        throw new IllegalStateException("Failed to retrieve user info");
    }

    public AccessTokenResponse refreshToken(String clientId, String clientSecret, String refreshToken) {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("refresh_token", refreshToken);
        body.add("grant_type", "refresh_token");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<AccessTokenResponse> responseEntity = restTemplate.postForEntity(
                "https://oauth2.googleapis.com/token",
                request,
                AccessTokenResponse.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        }

        throw new IllegalStateException("Failed to refresh access token");
    }
}
