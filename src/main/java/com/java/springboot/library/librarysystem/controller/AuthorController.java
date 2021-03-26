package com.java.springboot.library.librarysystem.controller;

import com.java.springboot.library.librarysystem.config.DataTransformer;
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
    private final DataTransformer dataTransformer;

    @Autowired
    public AuthorController(AuthorService authorService, DataTransformer dataTransformer) {
        this.authorService = authorService;
        this.dataTransformer = dataTransformer;
    }

    //TODO: rozdelit controller na -> books controller a author controller -> najprv prehod datatra. do servise vsrstiev
    //mvn clean install -Dmaven.test.skip=true

    @GetMapping() //done
    public ResponseEntity<List<AuthorDto>> viewAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @PostMapping() //done
    public ResponseEntity<IdDto> saveAuthor(@RequestBody AuthorDto authorDto) {

        IdDto id = authorService.saveAuthor(authorDto);

        return ResponseEntity.ok(id);
    }


    @DeleteMapping("/{id}") //done

    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {

        boolean result = authorService.deleteAuthor(id);

        if (result) {
            return ResponseEntity.ok().build();

        } else {
            return ResponseEntity.badRequest().build();

        }
    }
}
