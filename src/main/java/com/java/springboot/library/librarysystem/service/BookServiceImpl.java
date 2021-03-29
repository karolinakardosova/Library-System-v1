package com.java.springboot.library.librarysystem.service;


import com.java.springboot.library.librarysystem.config.DataTransformer;
import com.java.springboot.library.librarysystem.dto.BookDto;
import com.java.springboot.library.librarysystem.dto.IdDto;
import com.java.springboot.library.librarysystem.entity.AuthorEntity;
import com.java.springboot.library.librarysystem.entity.BookEntity;
import com.java.springboot.library.librarysystem.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional(readOnly = true) //vsade
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository repository;
    private final AuthorService authorService;
    private static final Logger LOG = LoggerFactory.getLogger(BookServiceImpl.class);

    private final DataTransformer dataTransformer;

    public BookServiceImpl(BookRepository repository,DataTransformer dataTransformer,AuthorService authorService) {
        this.repository = repository;
        this.dataTransformer = dataTransformer;
        this.authorService = authorService;
    }

    @Transactional(readOnly = false) //nad kazdou metodou co zapisuje tak musi mat false
    @Override
    public IdDto saveBook(BookDto bookDto,List<Long> authorIds){


        //transakcie co zacinaju maju mat vstup aj vystup dto
        //ked z controllera volam servisnu vrstvu zacinam transakciu
        //Nad kazdy servise dat transactional
        //Vloz sem authorServise

        BookEntity savedEntity = repository.save(dataTransformer.transform(bookDto,authorService.getAllAuthorsByID(authorIds)));
        LOG.info("id= {} ",savedEntity.getId());
        return new IdDto(savedEntity.getId());
    }

    @Transactional(readOnly = false)
    @Override
    public boolean deleteBook(long id){

        Optional<BookEntity> bookEntity = getOneByID(id);

        if (bookEntity.isPresent()) {
            repository.delete(bookEntity.get());
            return true;

        } else {
            return false;
        }

    }

    @Override
    public List<BookDto> getAllBooks(){


        List<BookEntity> entities = repository.findAll();
        List<BookDto> books = entities.stream().map(bookEntity -> dataTransformer.transform(bookEntity)).collect(Collectors.toList());

        return books;
    }



    @Override
    public Optional<BookEntity> getOneByID(long id){

        Optional<BookEntity> optional = repository.findById(id);
        return optional;

    }

    @Override
    public Optional<BookDto> getOneDtoByID(long id){

        Optional<BookEntity> optional = repository.findById(id);
        if (optional.isPresent()) {
            return Optional.of(dataTransformer.transform(optional.get()));
        } else {
            return Optional.empty();
        }

        /*
        Optional<BookEntity> optional = repository.findById(id);
        List<BookDto> list = new ArrayList<>();
        BookDto bookDto;

        if (optional.isPresent()){
            bookDto = dataTransformer.transform(optional.get());
            list.add(bookDto);
        }

        return list;

         */

    }

    @Transactional(readOnly = false)
    @Override
    public void updateBook(BookDto bookDto,List<Long> authorIds, long id){
        Optional<BookEntity> bookEntity = getOneByID(id);

        if(bookEntity.isPresent()){
            bookEntity.get().setAuthors(authorService.getAllAuthorsByID(authorIds));
            bookEntity.get().setTitle(bookDto.getTitle());
            repository.save(bookEntity.get());
        }

    }

}
