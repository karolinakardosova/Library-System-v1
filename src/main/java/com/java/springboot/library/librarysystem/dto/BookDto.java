package com.java.springboot.library.librarysystem.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BookDto {

    //Ako sa chovat pri dto ked mam authora ako stlpec
    private final String title;
    //private final List<String> authors;


    @JsonCreator
    public BookDto(
                    @JsonProperty("title") String title) {

        this.title = title;
        //this.author_id = author_id;
    }


    public String getTitle() {
        return title;
    }

    //public long getAuthorID() {
     //   return author_id;
    //}
}
