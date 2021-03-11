package com.java.springboot.library.librarysystem.service;

import com.java.springboot.library.librarysystem.entity.AuthorEntity;

import java.util.List;

public interface AuthorService {

     void saveAuthor(AuthorEntity authorEntity);

     void deleteAuthor(AuthorEntity authorEntity);

     List<AuthorEntity> getAllAuthors();

     AuthorEntity getAuthorByID(long id);

     void updateAuthor(AuthorEntity authorEntity);
}
