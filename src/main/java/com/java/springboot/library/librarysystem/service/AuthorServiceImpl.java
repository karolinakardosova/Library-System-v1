package com.java.springboot.library.librarysystem.service;

import com.java.springboot.library.librarysystem.config.DataTransformer;
import com.java.springboot.library.librarysystem.dto.AuthorDto;
import com.java.springboot.library.librarysystem.dto.IdDto;
import com.java.springboot.library.librarysystem.entity.AuthorEntity;
import com.java.springboot.library.librarysystem.repository.AuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
public class AuthorServiceImpl implements AuthorService {

    private static final Logger LOG = LoggerFactory.getLogger(AuthorServiceImpl.class);
    private final AuthorRepository repository;

    @Autowired
    private final DataTransformer dataTransformer;

    public AuthorServiceImpl(AuthorRepository repository, DataTransformer dataTransformer) {
        this.repository = repository;
        this.dataTransformer = dataTransformer;
    }

    @Transactional(readOnly = false)
    @Override
    public IdDto saveAuthor(AuthorDto authorDto) {
        AuthorEntity savedEntity = repository.save(dataTransformer.transform(authorDto));
        LOG.info("id= {} name = {}", savedEntity.getId(), savedEntity.getName());
        return new IdDto(savedEntity.getId());
    }

    @Transactional(readOnly = false)
    @Override
    public boolean deleteAuthor(long id) {
        Optional<AuthorEntity> authorEntity = getAuthorByID(id);
        if (authorEntity.isPresent()) {
            repository.delete(authorEntity.get());
            return true;

        } else {
            return false;
        }
    }

    @Override
    public List<AuthorDto> getAllAuthors() {
        List<AuthorEntity> entities = repository.findAll();
        return entities.stream().map(authorEntity -> dataTransformer.transform(authorEntity)).collect(Collectors.toList());
    }


    @Override
    public Optional<AuthorEntity> getAuthorByID(long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<AuthorDto> getAuthorDtoByID(long id) {
        Optional<AuthorEntity> optional = repository.findById(id);
        if (optional.isPresent()) {
            return Optional.of(dataTransformer.transform(optional.get()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public AuthorEntity getExceptionID(long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Author with this ID was not found"));
    }

    @Transactional(readOnly = false)
    @Override
    public void updateAuthor(AuthorDto authorDto, long id) {

        Optional<AuthorEntity> authorEntity = getAuthorByID(id);
        if (authorEntity.isPresent()) {
            authorEntity.get().setName(authorDto.getName());
            repository.save(authorEntity.get());
        }
    }

    @Override
    public List<AuthorEntity> getAllAuthorsByID(List<Long> idList) {
        List<AuthorEntity> found = new ArrayList<>();
        for (long id : idList) {
            Optional<AuthorEntity> optional = repository.findById(id);
            if (optional.isPresent()) {
                found.add(optional.get());
            }
        }
        return found;
    }

}
