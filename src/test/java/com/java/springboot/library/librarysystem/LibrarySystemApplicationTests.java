package com.java.springboot.library.librarysystem;

import com.java.springboot.library.librarysystem.dto.AuthorDto;
import com.java.springboot.library.librarysystem.dto.BookDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //--> aby som to nespustala externe
@ContextConfiguration
class LibrarySystemApplicationTests {

	@LocalServerPort
	int port;


	//Otazka na generated value od kadial to ide
	//TODO: inicializovat context

	@Autowired
	TestRestTemplate restTemplate;


	//TODO: service layer testy a full stack testy


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
		AuthorDto authorDto = new AuthorDto("Tolkien",1);
		String url = "/books/authors";

		ResponseEntity<AuthorDto> responseEntity = restTemplate.postForEntity(url, authorDto, AuthorDto.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

	}

	@Test
	@Order(3)
	void addSecondAuthor() {
		AuthorDto authorDto = new AuthorDto("Sapkowski",2);
		String url = "/books/authors";

		ResponseEntity<AuthorDto> responseEntity = restTemplate.postForEntity(url, authorDto, AuthorDto.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

	}

	@Test
	@Order(4)
	void addThirdAuthor() {
		AuthorDto authorDto = new AuthorDto("King",3);
		String url = "/books/authors";

		ResponseEntity<AuthorDto> responseEntity = restTemplate.postForEntity(url, authorDto, AuthorDto.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

	}

	@Test
	@Order(5)
	void checkAuthors() {

		String url = "/books/authors";

		ResponseEntity<AuthorDto[]> responseEntity = restTemplate.getForEntity(url, AuthorDto[].class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(3, responseEntity.getBody().length);
	}

	/*

	@Test
	@Order(6)
	void deleteFirstAuthor() {

		String url = "/books/authors/1";

		restTemplate.delete(url);
		ResponseEntity<AuthorDto[]> responseEntity = restTemplate.getForEntity(url, AuthorDto[].class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(2, responseEntity.getBody().length);
	}
	 */



///////////////////

	@Test
	@Order(6)
	void addFirstBook() {
		List<Long> authorsId = new ArrayList<Long>();
		authorsId.add((long)1);
		authorsId.add((long)2);
		BookDto bookEntity = new BookDto("Hobbit" , authorsId);
		String url = "/books";

		ResponseEntity<BookDto> responseEntity = restTemplate.postForEntity(url, bookEntity, BookDto.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

	}

	@Test
	@Order(7)
	void addSecondBook() {
		List<Long> authorsId = new ArrayList<Long>();
		authorsId.add((long)2);
		authorsId.add((long)3);
		BookDto bookEntity = new BookDto("Witcher" , authorsId);
		String url = "/books";

		ResponseEntity<BookDto> responseEntity = restTemplate.postForEntity(url, bookEntity, BookDto.class);
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
	@Order(9)
	void deleteFirstBook() {

		String url = "/books/4";

		restTemplate.delete(url);
		ResponseEntity<BookDto[]> responseEntity = restTemplate.getForEntity("/books", BookDto[].class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(1, responseEntity.getBody().length);
	}




}
