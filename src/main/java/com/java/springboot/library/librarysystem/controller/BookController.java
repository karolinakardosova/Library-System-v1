package com.java.springboot.library.librarysystem.controller;
import com.java.springboot.library.librarysystem.dto.AuthorDto;
import com.java.springboot.library.librarysystem.dto.BookDto;
import com.java.springboot.library.librarysystem.entity.AuthorEntity;
import com.java.springboot.library.librarysystem.entity.BookEntity;
import com.java.springboot.library.librarysystem.service.AuthorService;
import com.java.springboot.library.librarysystem.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

//TODO: !!!!!!!!!!! spytaj sa ho na to spojenie medzi tabulkami .. ked mam author_id ale pytam si entitu celu

@RestController
@RequestMapping("/books")
public class BookController {

    //TODO: dorob authora -> prehod to, kukni ako funguje docker, zacni tagy

    //TODO: -Testy , java doc, -sonar lint vycistit kod, -flyway db -> yaml ,

    //TODO: AfterMigrate.sql
    //V1 initial --> prve ... F6 na delete

    private final BookService bookService;
    private final AuthorService authorService;

    BookController(BookService bookService,AuthorService authorService) {

        this.bookService = bookService;
        this.authorService = authorService;
    }


    //TODO: opravit flyway, dokoncit controller, zapnut swagger, napisat unit testy na service layer a testy s pouizitm testcontainers
    //docker, docker compose

    private static BookDto transform(BookEntity entity) {
        return new BookDto( entity.getTitle());
    }

    private static BookEntity transform(BookDto dto) {
        return new BookEntity( dto.getTitle());
    }

    private static AuthorDto transform(AuthorEntity entity) {
        return new AuthorDto( entity.getName(),entity.getId());
    }

    private static AuthorEntity transform(AuthorDto dto) {
        return new AuthorEntity( dto.getName());
    }




    //Otazka ... mam to transofmrovat na service alebo controller vrstve?
    @PostMapping()
    //mal by vratit vytvorene ID knihy a nie void
    public ResponseEntity<Void> saveBook(@RequestBody BookDto bookDto){

        bookService.saveBook(transform(bookDto));

        return ResponseEntity.ok().build();
    }


    @GetMapping()
    public ResponseEntity<List<BookDto>> viewBooks(){
        List<BookEntity> entities = bookService.getAllBooks();
        List<BookDto> books = entities.stream().map(BookController::transform).collect(Collectors.toList());
        return ResponseEntity.ok(books);
    }




    //----------------------------------
    //TODO: na rest vrstve sa nepouzivaju dto, prerob vsetko na dto --
    //sudo systemctl enable --now docker
    //TODO: pridaj entitu a nech maju vztah, prirad ku kazdej knihe tag ( inverted sql table ) --> to ze mam key a value, kniha moze mat viac tagov
    //

    @PutMapping()
    public ResponseEntity<Void> updateBook(@RequestBody BookDto bookDto){

        bookService.saveBook(transform(bookDto));

        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")

    public ResponseEntity<Void> deleteBook(@PathVariable Long id){

        BookEntity bookEntity = bookService.getOneByID(id);
        if(bookEntity == null){
            return ResponseEntity.badRequest().build();
        }else{

            bookService.deleteBook(bookEntity);
            return ResponseEntity.ok().build();
        }
    }

    @GetMapping("/authors")
    public ResponseEntity<List<AuthorDto>> viewAuthors(){
        List<AuthorEntity> entities = authorService.getAllAuthors();
        List<AuthorDto> authors = entities.stream().map(BookController::transform).collect(Collectors.toList());
        return ResponseEntity.ok(authors);
    }
    @PostMapping("/authors")
    public ResponseEntity<Void> saveAuthor(@RequestBody AuthorDto authorDto){

        authorService.saveAuthor(transform(authorDto));

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/authors/{id}")

    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id){

        AuthorEntity authorEntity = authorService.getAuthorByID(id);
        if(authorEntity == null){
            return ResponseEntity.badRequest().build();
        }else{

            authorService.deleteAuthor(authorEntity);
            return ResponseEntity.ok().build();
        }
    }



}
