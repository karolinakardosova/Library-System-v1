package com.java.springboot.library.librarysystem.service;

import com.java.springboot.library.librarysystem.dto.AuthorDto;
import com.java.springboot.library.librarysystem.dto.IdDto;
import com.java.springboot.library.librarysystem.entity.AuthorEntity;


import java.util.List;
import java.util.Optional;

public interface AuthorService {

    /**
     * Creates new entity based on required Dto instance, which is transformed using DataTransformer.
     * Newly created AuthorEntity is then added into repository with new unique id which is returned as IdDto.
     *
     * @param authorDto - Dto instance containing necessary attributes to create Author entity.
     * @return IdDto instance of newly added entity in repository.
     */
    IdDto saveAuthor(AuthorDto authorDto);

    /**
     * Finds an Author entity by its unique id, if the entity is present it will delete it from repository, if entity is not present it will not be deleted and returns false.
     *
     * @param id - unique id of Author entity.
     * @return true if Author entity was found and deleted from repository, false otherwise.
     */
    boolean deleteAuthor(long id);

    /**
     * Returns a List of all Author Dto instances currently existing in author repository.
     *
     * @return List<AuthorDto>
     */
    List<AuthorDto> getAllAuthors();

    /**
     * Finds an Author entity by its unique id, if the entity is present it will return it as Optional object.
     *
     * @param id - unique id of Author entity.
     * @return Optional object which can hold value or not without returning null.
     */
    Optional<AuthorEntity> getAuthorByID(long id);

    /**
     * Finds an Author entity by its unique id, if the entity is present it will transform it into Dto instance and return it as Optional object.
     *
     * @param id - unique id of Author entity.
     * @return Optional object which can hold value or not without returning null.
     */
    Optional<AuthorDto> getAuthorDtoByID(long id);

    /**
     * Test method that calls for exception when no entity with unique id is found.
     *
     * @param id - unique id of Author entity.
     * @return Author entity found in repository.
     */
    AuthorEntity getExceptionID(long id);

    /**
     * Searches for an entity in repository by its unique id, if entity is present it will change its attributes to match Dto instance and save it back into repository.
     *
     * @param authorDto - Dto instance containing necessary attributes required to update existing entity in database.
     * @param id        - unique id of Author entity.
     */
    void updateAuthor(AuthorDto authorDto, long id);

    /**
     * Returns a list of all Author entities found in repository with matching ids from idList.
     *
     * @param idList - list of all unique Author ids
     * @return List<AuthorEntity>
     */
    List<AuthorEntity> getAllAuthorsByID(List<Long> idList);


}
