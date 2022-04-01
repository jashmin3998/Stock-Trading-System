package com.stockTrading.stockTradingSystem.Exception;

import com.stockTrading.stockTradingSystem.model.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = ArithmeticException.class)
    public Response handleArithmeticException(ArithmeticException e){
        return new Response(false,e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = InvalidUserException.class)
    public Response handleInvalidUserException(InvalidUserException e){
        return new Response(false,e.getErrorMsg());
    }



    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public Response handleEveryException(Exception e){
        return new Response(false,e.getMessage());
    }
}

