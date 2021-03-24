package com.java.springboot.library.librarysystem.controller;

import com.java.springboot.library.librarysystem.config.DataTransformer;
import com.java.springboot.library.librarysystem.dto.AuthorDto;
import com.java.springboot.library.librarysystem.dto.BookDto;
import com.java.springboot.library.librarysystem.dto.IdDto;
import com.java.springboot.library.librarysystem.dto.TagDto;
import com.java.springboot.library.librarysystem.entity.AuthorEntity;
import com.java.springboot.library.librarysystem.entity.BookEntity;
import com.java.springboot.library.librarysystem.entity.TagEntity;
import com.java.springboot.library.librarysystem.service.AuthorService;
import com.java.springboot.library.librarysystem.service.BookService;
import com.java.springboot.library.librarysystem.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final TagService tagService;
    private final DataTransformer dataTransformer;

    @Autowired
    BookController(BookService bookService, AuthorService authorService, TagService tagService, DataTransformer dataTransformer) {

        this.bookService = bookService;
        this.authorService = authorService;
        this.tagService = tagService;
        this.dataTransformer = dataTransformer;
    }


    //TODO: sprav malu klasu s id-ckami co mi ju budu wrappovat -> mozem to pouzivat aj na autorov aj knihy

    @PostMapping()
    public ResponseEntity<IdDto> saveBook(@RequestBody BookDto bookDto) {

        IdDto id = bookService.saveBook(dataTransformer.transform(bookDto, authorService.getAllAuthorsByID(bookDto.getAuthorsId())));

        return ResponseEntity.ok(id);
    }

    //TODO: lambda funkcie v jave
    @GetMapping()
    public ResponseEntity<List<BookDto>> viewBooks() {
        List<BookEntity> entities = bookService.getAllBooks();
        List<BookDto> books = entities.stream().map(bookEntity -> dataTransformer.transform(bookEntity)).collect(Collectors.toList());
        return ResponseEntity.ok(books);
    }


    @PutMapping()
    public ResponseEntity<Void> updateBook(@RequestBody BookDto bookDto) {

        bookService.saveBook(dataTransformer.transform(bookDto, authorService.getAllAuthorsByID(bookDto.getAuthorsId())));

        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")

    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {

        Optional<BookEntity> bookEntity = bookService.getOneByID(id);
        if (bookEntity.isPresent()) {
            return ResponseEntity.badRequest().build();
        } else {

            bookService.deleteBook(bookEntity.get());
            return ResponseEntity.ok().build();
        }
    }

    @GetMapping("/authors")
    public ResponseEntity<List<AuthorDto>> viewAuthors() {
        List<AuthorEntity> entities = authorService.getAllAuthors();
        List<AuthorDto> authors = entities.stream().map(authorEntity -> dataTransformer.transform(authorEntity)).collect(Collectors.toList());
        return ResponseEntity.ok(authors);
    }

    @PostMapping("/authors")
    public ResponseEntity<IdDto> saveAuthor(@RequestBody AuthorDto authorDto) {

        IdDto id = authorService.saveAuthor(dataTransformer.transform(authorDto));

        return ResponseEntity.ok(id);
    }


    @DeleteMapping("/authors/{id}")

    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {

        Optional<AuthorEntity> authorEntity = authorService.getAuthorByID(id);
        if (authorEntity.isPresent()) {
            return ResponseEntity.badRequest().build();
        } else {

            authorService.deleteAuthor(authorEntity.get());
            return ResponseEntity.ok().build();
        }
    }

    //-----------------------------------------------------
    @GetMapping("/tags")
    public ResponseEntity<List<TagDto>> viewTags() {
        List<TagEntity> entities = tagService.getAllTags();

        List<TagDto> tags = entities.stream().map(tagEntity -> dataTransformer.transform(tagEntity)).collect(Collectors.toList());
        return ResponseEntity.ok(tags);
    }

    @PostMapping("/tags")
    public ResponseEntity<Void> saveTag(@RequestBody TagDto tagDto) {

        tagService.saveTag(dataTransformer.transform(tagDto));

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/tags/{id}")

    public ResponseEntity<Void> deleteTag(@PathVariable String key) {


        return null;
    }


}
