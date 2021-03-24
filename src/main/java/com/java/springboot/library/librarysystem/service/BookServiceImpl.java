package com.java.springboot.library.librarysystem.service;


import com.java.springboot.library.librarysystem.dto.IdDto;
import com.java.springboot.library.librarysystem.entity.BookEntity;
import com.java.springboot.library.librarysystem.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class BookServiceImpl implements BookService {
    private final BookRepository repository;
    private static final Logger LOG = LoggerFactory.getLogger(BookServiceImpl.class);

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }


    @Override
    public IdDto saveBook(BookEntity bookEntity){
        BookEntity savedEntity = repository.save(bookEntity);
        LOG.info("id= {} ",savedEntity.getId());
        return new IdDto(savedEntity.getId());
    }

    @Override
    public void deleteBook(BookEntity bookEntity){
        repository.delete(bookEntity);
    }

    @Override
    public List<BookEntity> getAllBooks(){
        return repository.findAll();
    }



    @Override
    public Optional<BookEntity> getOneByID(long id){

        Optional<BookEntity> optional = repository.findById(id);
        return optional;

    }


    @Override
    public void update(BookEntity bookEntity){

        repository.save(bookEntity);

    }

}
