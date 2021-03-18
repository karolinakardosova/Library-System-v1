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


@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    BookController(BookService bookService, AuthorService authorService) {

        this.bookService = bookService;
        this.authorService = authorService;
    }

    private static BookDto transform(BookEntity entity) {
        return new BookDto(entity.getTitle(), entity.getAllAuthorsId());
    }


    private BookEntity transform(BookDto dto) {

        return new BookEntity(dto.getTitle(), authorService.getAllAuthorsByID(dto.getAuthors()));
    }

    private static AuthorDto transform(AuthorEntity entity) {
        return new AuthorDto(entity.getName(), entity.getId());
    }

    private static AuthorEntity transform(AuthorDto dto) {
        return new AuthorEntity(dto.getName());
    }




    @PostMapping()
    //mal by vratit vytvorene ID knihy a nie void
    public ResponseEntity<Void> saveBook(@RequestBody BookDto bookDto) {

        bookService.saveBook(transform(bookDto));

        return ResponseEntity.ok().build();
    }


    @GetMapping()
    public ResponseEntity<List<BookDto>> viewBooks() {
        List<BookEntity> entities = bookService.getAllBooks();
        List<BookDto> books = entities.stream().map(BookController::transform).collect(Collectors.toList());
        return ResponseEntity.ok(books);
    }


    @PutMapping()
    public ResponseEntity<Void> updateBook(@RequestBody BookDto bookDto) {

        bookService.saveBook(transform(bookDto));

        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")

    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {

        BookEntity bookEntity = bookService.getOneByID(id);
        if (bookEntity == null) {
            return ResponseEntity.badRequest().build();
        } else {

            bookService.deleteBook(bookEntity);
            return ResponseEntity.ok().build();
        }
    }

    @GetMapping("/authors")
    public ResponseEntity<List<AuthorDto>> viewAuthors() {
        List<AuthorEntity> entities = authorService.getAllAuthors();
        List<AuthorDto> authors = entities.stream().map(BookController::transform).collect(Collectors.toList());
        return ResponseEntity.ok(authors);
    }

    @PostMapping("/authors")
    public ResponseEntity<Void> saveAuthor(@RequestBody AuthorDto authorDto) {

        authorService.saveAuthor(transform(authorDto));

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/authors/{id}")

    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {

        AuthorEntity authorEntity = authorService.getAuthorByID(id);
        if (authorEntity == null) {
            return ResponseEntity.badRequest().build();
        } else {

            authorService.deleteAuthor(authorEntity);
            return ResponseEntity.ok().build();
        }
    }


}
