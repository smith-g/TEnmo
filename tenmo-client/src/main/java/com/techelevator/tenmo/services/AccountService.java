package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Accounts;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class AccountService {

    private String baseUrl;
    private RestTemplate restTemplate = new RestTemplate();

    private String authToken = null;

    public void  setAuthToken(String authToken){
        this.authToken = authToken;
    }

    public AccountService(String url) {
        this.baseUrl = url;
    }

    public Accounts[] getAllAccounts(){
        try {
            ResponseEntity<Accounts[]> response = restTemplate.exchange(baseUrl + "accounts", HttpMethod.GET, makeAuthEntity(),
                    Accounts[].class);
            return response.getBody();
        } catch (RestClientResponseException | ResourceAccessException ex){
            System.err.println("not found");
        }
        return null;
    }

    public boolean updateBalance(Accounts updatedAccount) {
        HttpEntity<Accounts> entity = makeAccountEntity(updatedAccount);

        boolean success = false;
        try {
            restTemplate.put(baseUrl + "pay/" + updatedAccount.getUser_id(),
                    entity);
            success = true;
        } catch (RestClientResponseException | ResourceAccessException e) {
            System.err.println(e.getMessage());
        }
        return success;
    }

    private HttpEntity<Accounts> makeAccountEntity(Accounts account) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(account, headers);
    }

    private HttpEntity<Void> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }
}
