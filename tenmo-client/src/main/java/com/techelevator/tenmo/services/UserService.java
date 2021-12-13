package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class UserService {

    private String baseUrl;
    private RestTemplate restTemplate = new RestTemplate();

    private String authToken = null;

    public void  setAuthToken(String authToken){
        this.authToken = authToken;
    }


    public UserService(String url) {
        this.baseUrl = url;
    }

    public User[] getAllUser(){
        try{
            ResponseEntity<User[]> response = restTemplate.exchange(baseUrl + "/users", HttpMethod.GET, makeAuthEntity(),
                    User[].class);
            return response.getBody();
        }catch (RestClientResponseException | ResourceAccessException ex){
            System.err.println("not found");
        }
        return null;
    }

    private HttpEntity<Void> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }
}
