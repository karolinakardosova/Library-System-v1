package com.java.springboot.library.librarysystem.service;

import com.java.springboot.library.librarysystem.dto.AuthorDto;
import com.java.springboot.library.librarysystem.dto.IdDto;
import com.java.springboot.library.librarysystem.entity.AuthorEntity;
import org.webjars.NotFoundException;


import java.util.List;
import java.util.Optional;

public interface AuthorService {

    IdDto saveAuthor(AuthorDto authorDto);

    boolean deleteAuthor(long id);

    List<AuthorDto> getAllAuthors();

    Optional<AuthorEntity> getAuthorByID(long id);

    Optional<AuthorDto> getAuthorDtoByID(long id);

    AuthorEntity getExceptionID(long id);

    void updateAuthor(AuthorDto authorDto, long id);

    List<AuthorEntity> getAllAuthorsByID(List<Long> idList);


}
