package com.java.springboot.library.librarysystem.config;

public class AuthorReferencedException extends RuntimeException {

    public AuthorReferencedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthorReferencedException(String message) {
        super(message);
    }
}
