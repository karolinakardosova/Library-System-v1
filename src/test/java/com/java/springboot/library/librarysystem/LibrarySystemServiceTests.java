package com.java.springboot.library.librarysystem;

import com.java.springboot.library.librarysystem.entity.AuthorEntity;
import com.java.springboot.library.librarysystem.service.AuthorService;
import com.java.springboot.library.librarysystem.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("h2")
public class LibrarySystemServiceTests {

    @Autowired
    AuthorService authorService;
    BookService bookService;

    @Test
    void testAuthorService() {

        assertEquals(0, authorService.getSizeOfAuthors());
        AuthorEntity entity = new AuthorEntity("Tolkien");
        assertNotNull(entity);
        authorService.saveAuthor(entity);
        assertEquals(1, authorService.getSizeOfAuthors());
        authorService.deleteAuthor(entity);
        assertEquals(0, authorService.getSizeOfAuthors());

    }



}
