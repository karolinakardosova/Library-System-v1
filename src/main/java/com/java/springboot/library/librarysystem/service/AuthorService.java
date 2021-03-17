package com.java.springboot.library.librarysystem.service;

import com.java.springboot.library.librarysystem.entity.AuthorEntity;

import java.util.ArrayList;
import java.util.List;

public interface AuthorService {
     /**
      *
      * @param authorEntity
      */

     void saveAuthor(AuthorEntity authorEntity);

     /**
      *
      * @param authorEntity
      */

     void deleteAuthor(AuthorEntity authorEntity);

     /**
      *
      * @return
      */

     List<AuthorEntity> getAllAuthors();

     /**
      *
      * @param id
      * @return
      */

     AuthorEntity getAuthorByID(long id);

     /**
      *
      * @param authorEntity
      */

     void updateAuthor(AuthorEntity authorEntity);

     /**
      *
      * @param idList
      * @return
      */
     List<AuthorEntity> getAllAuthorsByID(List<Long> idList);
}
