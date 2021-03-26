package com.java.springboot.library.librarysystem.service;


import com.java.springboot.library.librarysystem.config.DataTransformer;
import com.java.springboot.library.librarysystem.dto.AuthorDto;
import com.java.springboot.library.librarysystem.dto.IdDto;
import com.java.springboot.library.librarysystem.entity.AuthorEntity;
import com.java.springboot.library.librarysystem.repository.AuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private static final Logger LOG = LoggerFactory.getLogger(AuthorServiceImpl.class);

    private final AuthorRepository repository;

    @Autowired
    private final DataTransformer dataTransformer;

    public AuthorServiceImpl(AuthorRepository repository,DataTransformer dataTransformer) {
        this.repository = repository;
        this.dataTransformer = dataTransformer;
    }


    @Override
    public IdDto saveAuthor(AuthorDto authorDto){

       AuthorEntity savedEntity = repository.save(dataTransformer.transform(authorDto));
       LOG.info("id= {} name = {}",savedEntity.getId(),savedEntity.getName());
       return new IdDto (savedEntity.getId());
    }

    @Override
    public boolean deleteAuthor(long id){

        Optional<AuthorEntity> authorEntity = getAuthorByID(id);

        if (authorEntity.isPresent()) {
            repository.delete(authorEntity.get());
            return true;

        } else {
            return false;
        }

    }



    @Override
    public List<AuthorDto> getAllAuthors(){

        List<AuthorEntity> entities = repository.findAll();
        List<AuthorDto> authors = entities.stream().map(authorEntity -> dataTransformer.transform(authorEntity)).collect(Collectors.toList());

        return authors;
    }


    @Override
    public Optional<AuthorEntity> getAuthorByID(long id){

        Optional<AuthorEntity> optional = repository.findById(id);
        return optional;


    }

    @Override
    public void updateAuthor(AuthorEntity authorEntity){

        repository.save(authorEntity);

    }

    @Override
    public List<AuthorEntity> getAllAuthorsByID(List<Long> idList){

        List<AuthorEntity> found = new ArrayList<>();
        for (long id :idList) {

            //wrapper ktory vnutri ma alebo nema objekt -> na null pointer chyby

            Optional<AuthorEntity> optional = repository.findById(id);
            if(optional.isPresent()) {
                found.add(optional.get());
            }
        }

        return found;

    }


}
