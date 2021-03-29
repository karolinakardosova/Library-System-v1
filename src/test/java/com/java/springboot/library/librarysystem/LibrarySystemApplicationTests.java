package com.java.springboot.library.librarysystem;

import com.java.springboot.library.librarysystem.dto.AuthorDto;
import com.java.springboot.library.librarysystem.dto.BookDto;
import com.java.springboot.library.librarysystem.dto.IdDto;
import com.java.springboot.library.librarysystem.entity.BookEntity;
import com.java.springboot.library.librarysystem.service.AuthorServiceImpl;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.lifecycle.Startables;

import javax.validation.constraints.AssertFalse;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //--> aby som to nespustala externe
@ContextConfiguration(initializers = LibrarySystemApplicationTests.Initializer.class)
class LibrarySystemApplicationTests {

    private static long firstAuthorID;
    private static long secondAuthorID;
    private static long thirdAuthorID;
    private static long firstBookID;
    private static long secondBookID;
    private static final Logger LOG = LoggerFactory.getLogger(AuthorServiceImpl.class);


    @LocalServerPort
    int port;


    @Autowired
    TestRestTemplate restTemplate;


    //TODO: update a delete testy dorob -> na tagy testy a find by tag
    //TODO: negativne testy podorabaj

    @Test
    @Order(1)
    void getBooks() {

        String url = "/books";

        ResponseEntity<BookDto[]> responseEntity = restTemplate.getForEntity(url, BookDto[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(0, responseEntity.getBody().length);
    }


    @Test
    @Order(2)
    void addFirstAuthor() {
        AuthorDto authorDto = new AuthorDto("Tolkien");
        String url = "/authors";

        ResponseEntity<IdDto> responseEntity = restTemplate.postForEntity(url, authorDto, IdDto.class);
        firstAuthorID = responseEntity.getBody().getId();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    @Order(3)
    void addSecondAuthor() {
        AuthorDto authorDto = new AuthorDto("Sapkowski");
        String url = "/authors";

        ResponseEntity<IdDto> responseEntity = restTemplate.postForEntity(url, authorDto, IdDto.class);
        secondAuthorID = responseEntity.getBody().getId();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    @Order(4)
    void addThirdAuthor() {
        AuthorDto authorDto = new AuthorDto("King");
        String url = "/authors";

        ResponseEntity<IdDto> responseEntity = restTemplate.postForEntity(url, authorDto, IdDto.class);
        thirdAuthorID = responseEntity.getBody().getId();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    @Order(5)
    void checkAuthors() {

        String url = "/authors";

        ResponseEntity<AuthorDto[]> responseEntity = restTemplate.getForEntity(url, AuthorDto[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(3, responseEntity.getBody().length);
    }

    @Test
    @Order(5)
    void checkNonExistingAuthor() {

        String url = "/authors/300";

        ResponseEntity<AuthorDto> responseEntity = restTemplate.getForEntity(url, AuthorDto.class);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }

    @Test
    @Order(5)
    void checkNonExistingBooks() {

        String url = "/authors/300";

        ResponseEntity<BookDto> responseEntity = restTemplate.getForEntity(url, BookDto.class);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }


    @Test
    @Order(5)
    void updateFirstAuthor() {

        //TODO: odstran pole a nech to je len objekt jeden -> []

        String url = "/authors/" + firstAuthorID;
        AuthorDto authorDto = new AuthorDto("TOLKIEN");
        restTemplate.put(url, authorDto);
        ResponseEntity<AuthorDto> responseEntity = restTemplate.getForEntity(url, AuthorDto.class);
        LOG.info("id= {} name = {}", firstAuthorID, responseEntity.getBody().getName());
        assertFalse(responseEntity.getBody().getName() == "Tolkien");
    }


    @Test
    @Order(6)
    void addFirstBook() {
        List<Long> authorsId = new ArrayList<>();
        authorsId.add(firstAuthorID);

        BookDto bookEntity = new BookDto("Hobbit", authorsId);
        String url = "/books";
        ResponseEntity<IdDto> responseEntity = restTemplate.postForEntity(url, bookEntity, IdDto.class);
        firstBookID = responseEntity.getBody().getId();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    @Order(7)
    void addSecondBook() {
        List<Long> authorsId = new ArrayList<>();
        authorsId.add(thirdAuthorID);
        BookDto bookEntity = new BookDto("Witcher", authorsId);
        String url = "/books";

        ResponseEntity<IdDto> responseEntity = restTemplate.postForEntity(url, bookEntity, IdDto.class);
        secondBookID = responseEntity.getBody().getId();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    @Order(8)
    void checkAllBooks() {

        String url = "/books";

        ResponseEntity<BookDto[]> responseEntity = restTemplate.getForEntity(url, BookDto[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().length);
    }

    @Test
    @Order(8)
    void updateFirstBook() {
        List<Long> authorsId = new ArrayList<>();
        authorsId.add(firstAuthorID);
        authorsId.add(secondAuthorID);

        String url = "/books/" + firstBookID;
        BookDto bookDto = new BookDto("HOBBIT", authorsId);
        restTemplate.put(url, bookDto);
        ResponseEntity<BookDto> responseEntity = restTemplate.getForEntity(url, BookDto.class);

        LOG.info("id= {} name = {} authors = {}", firstBookID, responseEntity.getBody().getTitle(), responseEntity.getBody().getAuthorsId());

        assertEquals(responseEntity.getBody().getTitle(), bookDto.getTitle());
        List<Long> authors = responseEntity.getBody().getAuthorsId();
        Collections.sort(authors);
        assertEquals(authors, bookDto.getAuthorsId());
    }

    @Test
    @Order(9)
    void tryDeleteFirstAuthor() {

        String url = "/authors/" + firstAuthorID;

        restTemplate.delete(url);
        ResponseEntity<AuthorDto> responseEntity = restTemplate.getForEntity(url, AuthorDto.class);
        assertNotNull(responseEntity.getBody());
    }

    @Test
    @Order(10)
    void deleteFirstBook() {

        String url = "/books/" + firstBookID;

        restTemplate.delete(url);
        ResponseEntity<BookDto[]> responseEntity = restTemplate.getForEntity("/books", BookDto[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, responseEntity.getBody().length);
    }

    @Test
    @Order(11)
    void deleteFirstAuthor() {

        String url = "/authors/" + firstAuthorID;

        restTemplate.delete(url);
        ResponseEntity<AuthorDto> responseEntity = restTemplate.getForEntity(url, AuthorDto.class);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
        ResponseEntity<AuthorDto[]> responseEntities = restTemplate.getForEntity("/authors", AuthorDto[].class);
        assertEquals(HttpStatus.OK, responseEntities.getStatusCode());
        assertEquals(2, responseEntities.getBody().length);
    }


    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:12.3");

        private static void startContainers() {
            Startables.deepStart(Stream.of(postgres)).join();
        }

        private static Map<String, String> createContextConfiguration() {
            return Map.of(
                    "spring.datasource.url", postgres.getJdbcUrl(),
                    "spring.datasource.username", postgres.getDatabaseName(),
                    "spring.datasource.password", postgres.getPassword()
            );
        }

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            startContainers();
            ConfigurableEnvironment environment = applicationContext.getEnvironment();
            MapPropertySource testContainers = new MapPropertySource("testcontainers", (Map) createContextConfiguration());
            environment.getPropertySources().addFirst(testContainers);
        }
    }


}
