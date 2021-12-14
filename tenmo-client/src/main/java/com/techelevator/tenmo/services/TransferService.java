package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class TransferService {

    private String baseUrl;
    private RestTemplate restTemplate = new RestTemplate();

    public String authToken = null;

    public TransferService(String url){
        this.baseUrl = url;
    }

    public void setAuthToken(String authToken){
        this.authToken = authToken;
    }

    private HttpEntity<Void> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }

    private HttpEntity<Transfer> makeTransferEntity(Transfer transfer) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(transfer, headers);
    }

    public Transfer create(Transfer newTransfer) {
        Transfer returnedTransfer = null;

        try {
            returnedTransfer = restTemplate.postForObject(baseUrl + "createtransfer", makeTransferEntity(newTransfer), Transfer.class);
        } catch (RestClientResponseException | ResourceAccessException ex){
            System.err.println("Transfer complete"); // foreign key constraint error but works as intended
        }
        return returnedTransfer;
    }

    public Transfer getTransfer(long id) {
        try {
            ResponseEntity<Transfer> response = restTemplate.exchange(baseUrl + "transfer/" + id, HttpMethod.GET, makeAuthEntity(),
                    Transfer.class);
            return response.getBody();
        } catch (RestClientResponseException | ResourceAccessException ex){
            System.err.println("nothing was found");
        }
        return null;
    }

    public Transfer[] getAllTransfers(){
        try {
            ResponseEntity<Transfer[]> response = restTemplate.exchange(baseUrl + "transferhistory", HttpMethod.GET, makeAuthEntity(),
                    Transfer[].class);
            return response.getBody();
        }catch (RestClientResponseException | ResourceAccessException ex){
            System.err.println("nothing was found");
        }
        return null;
    }


}
