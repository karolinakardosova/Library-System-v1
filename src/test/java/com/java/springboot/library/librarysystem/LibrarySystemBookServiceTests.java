package com.java.springboot.library.librarysystem;

import com.java.springboot.library.librarysystem.dto.AuthorDto;
import com.java.springboot.library.librarysystem.dto.BookDto;
import com.java.springboot.library.librarysystem.dto.IdDto;
import com.java.springboot.library.librarysystem.service.AuthorService;
import com.java.springboot.library.librarysystem.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles(value = "h2", inheritProfiles = false)
@Profile("h2")
public class LibrarySystemBookServiceTests {

    @Autowired
    AuthorService authorService;
    @Autowired
    BookService bookService;


    @Test
    void testBookService() {
        assertEquals(0, bookService.getAllBooks().size());
        AuthorDto authorEntity = new AuthorDto("Tolkien");
        assertNotNull(authorEntity);
        IdDto id = authorService.saveAuthor(authorEntity);
        List<Long> listID = new ArrayList<>();
        listID.add(id.getId());
        BookDto bookEntity = new BookDto("Lotr", listID);
        IdDto idBook = bookService.saveBook(bookEntity, listID);
        assertEquals(1, bookService.getAllBooks().size());
        bookService.deleteBook(idBook.getId());
        assertEquals(0, bookService.getAllBooks().size());

    }
}
