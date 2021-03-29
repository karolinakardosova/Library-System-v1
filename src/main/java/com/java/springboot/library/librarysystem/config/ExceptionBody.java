package com.java.springboot.library.librarysystem.config;

import org.springframework.http.HttpStatus;


public class ExceptionBody {
    private final String message;
    private final HttpStatus httpStatus;


    public ExceptionBody(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
