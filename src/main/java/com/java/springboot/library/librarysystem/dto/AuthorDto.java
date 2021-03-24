package com.java.springboot.library.librarysystem.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthorDto {

    private final String name;


    @JsonCreator
    public AuthorDto(
            @JsonProperty("name") String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }




}
