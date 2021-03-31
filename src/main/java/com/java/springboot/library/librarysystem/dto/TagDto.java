package com.java.springboot.library.librarysystem.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class TagDto {

    private final String key;
    private final String value;


    @JsonCreator
    public TagDto(
            @JsonProperty("key") String key,
            @JsonProperty("value") String value) {

        this.key = key;
        this.value = value;

    }


    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
