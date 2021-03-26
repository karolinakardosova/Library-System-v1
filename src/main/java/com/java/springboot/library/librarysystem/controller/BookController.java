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

    //TODO: vyhodila z controlleru datatransformer a dat to do servisnej vrstvy -> vyhodim z tadeto vyhodim datatra. a nainjectujem ho do servisnych beanov
    //TODO: na controllerovej vrstve by som nemala pracovat priamo s entitami napr LIST<BoonEntity>

    @PostMapping()
    public ResponseEntity<IdDto> saveBook(@RequestBody BookDto bookDto) {

        IdDto id = bookService.saveBook(bookDto,bookDto.getAuthorsId());

        return ResponseEntity.ok(id);
    }


    @GetMapping() //done
    public ResponseEntity<List<BookDto>> viewBooks() {

        return ResponseEntity.ok(bookService.getAllBooks());
    }


    @PutMapping()
    public ResponseEntity<Void> updateBook(@RequestBody BookDto bookDto) {

        bookService.saveBook(bookDto,bookDto.getAuthorsId());

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
