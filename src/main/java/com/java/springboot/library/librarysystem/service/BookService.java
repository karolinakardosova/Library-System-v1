package com.java.springboot.library.librarysystem.service;

import com.java.springboot.library.librarysystem.dto.BookDto;
import com.java.springboot.library.librarysystem.dto.IdDto;
import com.java.springboot.library.librarysystem.entity.BookEntity;

import java.util.List;
import java.util.Optional;


public interface BookService {
    /**
     * Creates new entity based on required Dto object, which is transformed using DataTransformer, it also creates a M:N relationship between Book and Author entity.
     * Newly created BookEntity is then added into repository with new unique id which is returned as IdDto.
     * @param bookDto - Dto instance containing necessary attributes to create Book entity.
     * @param authorIds - List of author ids that exists in AuthorRepository.
     * @return IdDto instance of newly added entity in repository.
     */
    IdDto saveBook(BookDto bookDto, List<Long> authorIds);

    /**
     * Finds an Book entity by its unique id, if the entity is present it will delete it from repository, if entity is not present it will not be deleted and returns false.
     * @param id - unique id of Book entity.
     * @return true if Book entity was found and deleted from repository, false otherwise.
     */
    boolean deleteBook(long id);

    /**
     * Returns a List of all Book Dto instances currently existing in book repository.
     * @return List<BookDto>
     */
    List<BookDto> getAllBooks();

    /**
     * Finds a Book entity by its unique id, if the entity is present it will return it as Optional object.
     * @param id - unique id of Book entity.
     * @return Optional object which can hold value or not without returning null.
     */
    Optional<BookEntity> getOneByID(long id);

    /**
     * Finds a Book entity by its unique id, if the entity is present it will transform it into Dto instance and return it as Optional object.
     * @param id - unique id of Book entity.
     * @return Optional object which can hold value or not without returning null.
     */
    Optional<BookDto> getOneDtoByID(long id);


    /**
     * Searches for an entity in repository by its unique id, if entity is present it will change its attributes to match Dto instance and list of authors and save it back into repository.
     * @param bookDto - Dto instance containing necessary attributes required to update existing entity in database.
     * @param authorIds - List of author ids.
     * @param id - unique id of Book entity.
     */
    void updateBook(BookDto bookDto, List<Long> authorIds, long id);

}
