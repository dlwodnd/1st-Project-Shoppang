package com.project.test.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestController
@ControllerAdvice // 전역에 발생할 수 있는 예외를 처리해줌
@Slf4j
public class TestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    //모든 예외를 처리하는 메소드
    //Bean 내에서 발생하는 예외를 처리
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ExcepetionResponse excepetionResponse
                = new ExcepetionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(excepetionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PurchaseProductException.class)
    public final ResponseEntity<Object> handleCustomExceptions(PurchaseProductException ex, WebRequest request) {
        ExcepetionResponse excepetionResponse
                = new ExcepetionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(excepetionResponse, HttpStatus.BAD_REQUEST);
    }


}
