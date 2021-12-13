package com.techelevator.tenmo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Account not found.")
public class AccountException extends Exception{
    private static final long serialVersionUID = 1L;

    AccountException(){
        super("Account not found.");
    }


}
