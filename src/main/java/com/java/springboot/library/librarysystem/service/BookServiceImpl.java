package com.java.springboot.library.librarysystem.service;

import com.java.springboot.library.librarysystem.entity.BookEntity;
import com.java.springboot.library.librarysystem.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BookServiceImpl implements BookService {
    private final BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }


    @Override
    public void saveBook(BookEntity bookEntity){
        repository.save(bookEntity);
    }

    @Override
    public void deleteBook(BookEntity bookEntity){
        repository.delete(bookEntity);
    }

    @Override
    public List<BookEntity> getAllBooks(){
        return repository.findAll();
    }

    // TODO: vrat optional nie null

    @Override
    public BookEntity getOneByID(long id){

        if(repository.findById(id).isPresent()){
            return repository.findById(id).get();
        }else {
            return null;
        }
        //Optional<Book>

    }
    /*
    @Override
    public List<BookEntity> getAllBooksByAuthor(String author){
        List<BookEntity> found = new ArrayList<>();
        for (BookEntity bookEntity :repository.findAll()) {
            if (bookEntity.getTitle().equals(author)){
                found.add(bookEntity);
            }
        }

        return found;
    }



    @Override
    public List<BookEntity> getAllBooksByTitle(String name){
        List<BookEntity> found = new ArrayList<>();
        for (BookEntity bookEntity :repository.findAll()) {
            if (bookEntity.getAuthor().equals(name)){
                found.add(bookEntity);
            }
        }
        return found;
    }
    */

    @Override
    public void update(BookEntity bookEntity){

        repository.save(bookEntity);

    }

}
