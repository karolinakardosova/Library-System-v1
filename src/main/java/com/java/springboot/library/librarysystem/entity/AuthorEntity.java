package com.java.springboot.library.librarysystem.entity;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="AUTHOR")
public class AuthorEntity {

    private @Id
    @GeneratedValue
    @Column(name="author_id")
    long author_id;

    @Column(name="name")
    @JsonProperty("name")
    private String name;




    //TODO: kniha by mala autorov


    public AuthorEntity(String name){
        this.name = name;

    }

    public AuthorEntity() {
    }

    public long getId(){
        return author_id;
    }

    public void setId(long id) {
        this.author_id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
