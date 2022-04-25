package com.battle.movie.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {


    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public String handle(RuntimeException exception) {
        log.error("error", exception);
        return exception.getMessage();
    }
}
