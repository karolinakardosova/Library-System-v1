package com.java.springboot.library.librarysystem.service;

public class AuthorReferencedException extends RuntimeException{
    public AuthorReferencedException(String message) {
        super(message); //-> volam konstruktor predka
        //TODO: exception urob
        //TODO: napr author distance is still in use pls delete -> help message
        //napr ak mazem autora co tam nie je -> overridnem NoSuchElementException
        //v configu vytvorit novu vlassu -> ControllerADvice a tam to pisem
    }
}
