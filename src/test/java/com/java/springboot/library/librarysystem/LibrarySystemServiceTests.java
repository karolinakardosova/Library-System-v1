package com.java.springboot.library.librarysystem;

import com.java.springboot.library.librarysystem.entity.AuthorEntity;
import com.java.springboot.library.librarysystem.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//TODO: vytvorit profile na testy - nastavit test runtime aby nemergoval profil z normalneho aplikacneho runtimu

@SpringBootTest
@ActiveProfiles(value="h2",inheritProfiles = false)
@Profile("h2")
public class LibrarySystemServiceTests {

    @Autowired
    AuthorService authorService;

    @Test
    void testAuthorService() {

        assertEquals(0, authorService.getAllAuthors().size());
        AuthorEntity entity = new AuthorEntity("Tolkien");
        assertNotNull(entity);
        authorService.saveAuthor(entity);
        assertEquals(1, authorService.getAllAuthors().size());
        authorService.deleteAuthor(entity);
        assertEquals(0, authorService.getAllAuthors().size());

    }



}
