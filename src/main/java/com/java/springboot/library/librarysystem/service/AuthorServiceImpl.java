package com.java.springboot.library.librarysystem.service;

import com.java.springboot.library.librarysystem.entity.AuthorEntity;
import com.java.springboot.library.librarysystem.entity.BookEntity;
import com.java.springboot.library.librarysystem.repository.AuthorRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository repository;

    public AuthorServiceImpl(AuthorRepository repository) {
        this.repository = repository;
    }


    @Override
    public void saveAuthor(AuthorEntity authorEntity){
        repository.save(authorEntity);
    }

    @Override
    public void deleteAuthor(AuthorEntity authorEntity){
        repository.delete(authorEntity);
    }

    @Override
    public List<AuthorEntity> getAllAuthors(){
        return repository.findAll();
    }


    @Override
    public AuthorEntity getAuthorByID(long id){

        if(repository.findById(id).isPresent()){
            return repository.findById(id).get();
        }else {
            return null;
        }

    }

    @Override
    public void updateAuthor(AuthorEntity authorEntity){

        repository.save(authorEntity);

    }

    @Override
    public List<AuthorEntity> getAllAuthorsByID(List<Long> idList){

        List<AuthorEntity> found = new ArrayList<>();
        for (long id :idList) {
            found.add(repository.findById(id).get());
        }

        return found;

    }
}
