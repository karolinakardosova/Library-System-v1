package com.java.springboot.library.librarysystem.controller;


import com.java.springboot.library.librarysystem.dto.AuthorDto;
import com.java.springboot.library.librarysystem.dto.IdDto;
import com.java.springboot.library.librarysystem.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @GetMapping()
    public ResponseEntity<List<AuthorDto>> viewAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());

    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> viewAuthors(@PathVariable Long id) {
        return ResponseEntity.of(authorService.getAuthorDtoByID(id));
    }

    @PostMapping()
    public ResponseEntity<IdDto> saveAuthor(@RequestBody AuthorDto authorDto) {
        IdDto id = authorService.saveAuthor(authorDto);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        boolean result = authorService.deleteAuthor(id);
        if (result) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAuthor(@RequestBody AuthorDto authorDto, @PathVariable Long id) {
        authorService.updateAuthor(authorDto, id);
        return ResponseEntity.ok().build();
    }
}
