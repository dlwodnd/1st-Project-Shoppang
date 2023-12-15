package com.project.test.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PurchaseProductException extends RuntimeException{
    private String errorCode;
    public PurchaseProductException(String message){
        super(message);

    }


    public PurchaseProductException(String message,String errorCode){
        super(message);
        this.errorCode = errorCode;

    }

    public String getErrorCode() {
        return errorCode;
    }
}
