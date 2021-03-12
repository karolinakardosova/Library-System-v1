package com.java.springboot.library.librarysystem.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthorDto {

    private final String name;
    private final long id;


    @JsonCreator
    public AuthorDto(
            @JsonProperty("name") String name,
            @JsonProperty("id") long id) {

        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }


}
