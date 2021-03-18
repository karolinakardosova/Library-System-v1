package com.java.springboot.library.librarysystem.entity;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name="AUTHOR")
public class AuthorEntity {

    private @Id
    @GeneratedValue
    @Column(name="author_id")
    long authorId;

    @Column(name="name")
    @JsonProperty("name")
    private String name;



    public AuthorEntity(String name){
        this.name = name;

    }

    public AuthorEntity() {
    }

    public long getId(){
        return authorId;
    }

    public void setId(long id) {
        this.authorId = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
