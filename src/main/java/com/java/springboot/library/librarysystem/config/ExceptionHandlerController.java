package com.java.springboot.library.librarysystem.config;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(AuthorReferencedException.class)
    public ResponseEntity<Object> handleAuthorReferencedException(AuthorReferencedException e) {
        ExceptionBody exceptionBody = new ExceptionBody(
                e.getMessage(),
                HttpStatus.BAD_REQUEST
        );
        return new ResponseEntity<>(exceptionBody, HttpStatus.BAD_REQUEST);
    }


}
