package com.example.CricketTeam.CricketException;


import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<?> playerNotFoundException(PlayerNotFoundException ex, WebRequest request)
    {
        ExceptionDetails exceptionDetails=new ExceptionDetails(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
    }


}
