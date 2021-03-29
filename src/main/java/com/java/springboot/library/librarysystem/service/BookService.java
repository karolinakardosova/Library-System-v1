package com.java.springboot.library.librarysystem.service;

import com.java.springboot.library.librarysystem.dto.BookDto;
import com.java.springboot.library.librarysystem.dto.IdDto;
import com.java.springboot.library.librarysystem.entity.BookEntity;

import java.util.List;
import java.util.Optional;


public interface BookService {

    IdDto saveBook(BookDto bookDto, List<Long> authorIds);


    boolean deleteBook(long id);

    /**
     * Returns a ist of all the books in repository.
     *
     * @return List<BookEntity>
     */
    List<BookDto> getAllBooks();

    /**
     * Returns and entity with the same id.
     *
     * @param id - unique entity identifier
     * @return BookEntity or null
     */
    Optional<BookEntity> getOneByID(long id);

    Optional<BookDto> getOneDtoByID(long id);


    /**
     * Updates the existing entity in the repository.
     *
     * @param bookDto
     * @param authorIds
     * @param id
     */
    void updateBook(BookDto bookDto, List<Long> authorIds, long id);

}
