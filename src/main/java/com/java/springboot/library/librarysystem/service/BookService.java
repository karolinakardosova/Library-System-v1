package com.java.springboot.library.librarysystem.service;

import com.java.springboot.library.librarysystem.entity.BookEntity;

import java.util.List;

//TODO: popis hranicne pripady -> viac popis co to robi
public interface BookService {
     /**
      * Adds entity into repository.
      * @param bookEntity - Object instance
      */
     void saveBook(BookEntity bookEntity);

     /**
      * Deletes entity from repository.
      * @param bookEntity - Object instance
      */
     void deleteBook(BookEntity bookEntity);

     /**
      * Returns a ist of all the books in repository.
      * @return List<BookEntity>
      */
     List<BookEntity> getAllBooks();

     /**
      * Returns and entity with the same id.
      * @param id - unique entity identifier
      * @return BookEntity or null
      */
     BookEntity getOneByID(long id);

     /**
      * Returns a List of entities from the same author.
      * @param author - String
      * @return List<BookEntity>
      */

     //List<BookEntity> getAllBooksByAuthor(String author);

     /**
      * Returns a List of entities with the same name.
      * @param name - String
      * @return List<BookEntity>
      */
    // List<BookEntity> getAllBooksByTitle(String name);

     /**
      * Updates the existing entity in the repository.
      * @param bookEntity - Object instance
      */
     void update(BookEntity bookEntity);

}
