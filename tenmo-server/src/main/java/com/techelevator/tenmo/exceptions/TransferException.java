package com.techelevator.tenmo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "ID was not found")
public class TransferException extends Exception{

    public TransferException() {
        super("ID was not found");
    }
}
