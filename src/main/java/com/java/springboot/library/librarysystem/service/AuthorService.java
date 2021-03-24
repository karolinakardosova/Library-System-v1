package com.java.springboot.library.librarysystem.service;

import com.java.springboot.library.librarysystem.dto.IdDto;
import com.java.springboot.library.librarysystem.entity.AuthorEntity;


import java.util.List;
import java.util.Optional;

public interface AuthorService {
     /**
      *
      * @param authorEntity
      */

     IdDto saveAuthor(AuthorEntity authorEntity);

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

     Optional<AuthorEntity> getAuthorByID(long id);

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
