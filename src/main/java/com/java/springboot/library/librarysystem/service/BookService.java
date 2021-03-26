package com.java.springboot.library.librarysystem.service;

import com.java.springboot.library.librarysystem.dto.BookDto;
import com.java.springboot.library.librarysystem.dto.IdDto;
import com.java.springboot.library.librarysystem.entity.AuthorEntity;
import com.java.springboot.library.librarysystem.entity.BookEntity;

import java.util.List;
import java.util.Optional;


public interface BookService {
     /**
      * Adds entity into repository.
      * @param bookEntity - Object instance
      */
     IdDto saveBook(BookDto bookDto,List<Long> authorIds);

     /**
      * Deletes entity from repository.
      * @param bookEntity - Object instance
      */
     boolean deleteBook(long id);

     /**
      * Returns a ist of all the books in repository.
      * @return List<BookEntity>
      */
     List<BookDto> getAllBooks();

     /**
      * Returns and entity with the same id.
      * @param id - unique entity identifier
      * @return BookEntity or null
      */
     Optional<BookEntity> getOneByID(long id);



     /**
      * Updates the existing entity in the repository.
      * @param bookEntity - Object instance
      */
     void update(BookEntity bookEntity);

}
