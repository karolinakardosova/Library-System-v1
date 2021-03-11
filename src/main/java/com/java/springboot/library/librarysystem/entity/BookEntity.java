package com.java.springboot.library.librarysystem.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="BOOK")
public class BookEntity {
    private @Id
    @GeneratedValue
    long id; //lepsie pouzit randomuid


    @Column(name="title")
    @JsonProperty("title")
    private String title;
    /*
    @Column(name="author")
    @JsonProperty("author")
    private String author;
    */

    //TODO: OnetoMany alebo onetoone
    //TODO: id (foreign),key value pre entitu, typ entity, id zaznamu( primar) --> universal property tabulka
    //Tabulka - netypove hodnoty

    //kniha -> autor nemusi mat ziadnu relaciu, kniha moze mat viac autorov --> onetomany
    //na onetomany neurobim joincollumn
   // @OneToOne (targetEntity = BookEntity.class, )

    @OneToMany(cascade = CascadeType.ALL)
    private List<AuthorEntity> authors;


    public BookEntity(String title){
        this.title = title;

    }

    public BookEntity() {
    }

    public long getId(){
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String cont) {
        this.title = cont;
    }

    public List<AuthorEntity> getAuthors(){
        return authors;
    }

    public void setAuthors(List<AuthorEntity> authors) {
        this.authors = authors;
    }

    public boolean checkAuthorList(AuthorEntity authorEntity){

        boolean value = true;

        if(authors.isEmpty()){
            value = false;
        }else {
            for (AuthorEntity author: authors) {
                if(author.getId() == authorEntity.getId()){
                    value = true;
                    break;
                }else{
                    value = false;
                }

            }
        }

    return value;
    }



    public void addAuthor(AuthorEntity authorEntity){
        if(checkAuthorList(authorEntity)==false){
            authors.add(authorEntity);
        }

    }

    public void deleteAuthor(AuthorEntity authorEntity){
        authors.remove(authorEntity);

    }







}
