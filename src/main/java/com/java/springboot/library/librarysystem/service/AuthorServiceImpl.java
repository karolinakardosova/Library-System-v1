package com.java.springboot.library.librarysystem.service;


import com.java.springboot.library.librarysystem.dto.IdDto;
import com.java.springboot.library.librarysystem.entity.AuthorEntity;
import com.java.springboot.library.librarysystem.repository.AuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private static final Logger LOG = LoggerFactory.getLogger(AuthorServiceImpl.class);

    private final AuthorRepository repository;

    public AuthorServiceImpl(AuthorRepository repository) {
        this.repository = repository;
    }


    @Override
    public IdDto saveAuthor(AuthorEntity authorEntity){

       AuthorEntity savedEntity = repository.save(authorEntity);
       LOG.info("id= {} name = {}",savedEntity.getId(),savedEntity.getName());
       return new IdDto (savedEntity.getId());
    }

    @Override
    public void deleteAuthor(AuthorEntity authorEntity){
        repository.delete(authorEntity);
    }

    @Override
    public List<AuthorEntity> getAllAuthors(){
        return repository.findAll();
    }

    //TODO: Toto cele mi ma vratit optional -> prerob vsetky na optional nech mi to nevracia null
    //TODO: lambdy, transactional pozri - isolation, propagation
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
