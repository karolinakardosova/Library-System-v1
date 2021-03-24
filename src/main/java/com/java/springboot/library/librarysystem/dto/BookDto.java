package com.java.springboot.library.librarysystem.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BookDto {


    private final String title;
    private final List<Long> authorsId;


    @JsonCreator
    public BookDto(
                    @JsonProperty("title") String title,
                    @JsonProperty("authorsId") List<Long> authorsId) {

        this.title = title;
        this.authorsId = authorsId;

    }


    public String getTitle() {
        return title;
    }

    public List<Long> getAuthorsId() {
        return authorsId;
    }
}
