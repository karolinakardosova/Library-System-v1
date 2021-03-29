package com.java.springboot.library.librarysystem.controller;

import com.java.springboot.library.librarysystem.dto.BookDto;
import com.java.springboot.library.librarysystem.dto.IdDto;
import com.java.springboot.library.librarysystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    BookController(BookService bookService) {

        this.bookService = bookService;
    }



    @PostMapping() //done
    public ResponseEntity<IdDto> saveBook(@RequestBody BookDto bookDto) {

        IdDto id = bookService.saveBook(bookDto,bookDto.getAuthorsId());

        return ResponseEntity.ok(id);
    }


    @GetMapping() //done
    public ResponseEntity<List<BookDto>> viewBooks() {

        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}") //done
    public ResponseEntity<BookDto> viewBookById(@PathVariable Long id) {
        return ResponseEntity.of(bookService.getOneDtoByID(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBook(@RequestBody BookDto bookDto,@PathVariable Long id) {

        bookService.updateBook(bookDto,bookDto.getAuthorsId(),id);

        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")//done

    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {

        boolean result = bookService.deleteBook(id);

        if (result) {
            return ResponseEntity.ok().build();

        } else {
            return ResponseEntity.badRequest().build();

        }
    }







}
