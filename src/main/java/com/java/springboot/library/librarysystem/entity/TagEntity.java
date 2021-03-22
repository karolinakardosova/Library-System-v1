package com.java.springboot.library.librarysystem.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name="TAG")
public class TagEntity {



    @Id
    @Column(name="key")
    @JsonProperty("key")
    private String key;

    @Column(name="value")
    @JsonProperty("value")
    private String value;



    public TagEntity(String key,String value){
        this.value = value;

    }

    public TagEntity() {
    }

    public String getKey(){
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue(){
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
