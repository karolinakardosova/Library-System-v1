package com.java.springboot.library.librarysystem.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class IdDto {

    private final long id;


    @JsonCreator
    public IdDto(
            @JsonProperty("id") Long id) {

        this.id = id;

    }

    public long getId() {
        return id;
    }
}
