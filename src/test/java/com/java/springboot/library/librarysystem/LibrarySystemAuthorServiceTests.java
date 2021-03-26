package com.java.springboot.library.librarysystem;

import com.java.springboot.library.librarysystem.dto.AuthorDto;
import com.java.springboot.library.librarysystem.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//TODO: dorob testy pre book a tag service --> testy pokryvaju aj negativne scenare

@SpringBootTest
@ActiveProfiles(value="h2",inheritProfiles = false)
@Profile("h2")
class LibrarySystemAuthorServiceTests {

    @Autowired
    AuthorService authorService;

    @Test
    void testAuthorService() {

        assertEquals(0, authorService.getAllAuthors().size());
        AuthorDto entity = new AuthorDto("Tolkien");
        assertNotNull(entity);
        authorService.saveAuthor(entity);
        assertEquals(1, authorService.getAllAuthors().size());
        authorService.deleteAuthor(1);
        assertEquals(0, authorService.getAllAuthors().size());

    }



}
