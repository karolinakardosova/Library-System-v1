package com.java.springboot.library.librarysystem;

import com.java.springboot.library.librarysystem.dto.BookDto;
import com.java.springboot.library.librarysystem.entity.BookEntity;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


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

	/*
	@Test
	@Order(1)
	void getBooks() {

		String url = "/books";

		ResponseEntity<BookDto[]> responseEntity = restTemplate.getForEntity(url, BookDto[].class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(1, responseEntity.getBody().length);
	}

	@Test
	@Order(2)
	void addFirstBook() {
		BookDto bookEntity = new BookDto("Hobbit" , "J. R. R. Tolkien");
		String url = "/books";

		ResponseEntity<BookDto> responseEntity = restTemplate.postForEntity(url, bookEntity, BookDto.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

	}

	@Test
	@Order(3)
	void checkBook() {

		String url = "/books";

		ResponseEntity<BookDto[]> responseEntity = restTemplate.getForEntity(url, BookDto[].class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(2, responseEntity.getBody().length);
	}

	@Test
	@Order(4)
	void addSecondBook() {
		BookDto bookEntity = new BookDto("Shining" , "Stephen King");
		String url = "/books";

		ResponseEntity<BookDto> responseEntity = restTemplate.postForEntity(url, bookEntity, BookDto.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

	}

	@Test
	@Order(5)
	void checkBooks() {

		String url = "/books";

		ResponseEntity<BookDto[]> responseEntity = restTemplate.getForEntity(url, BookDto[].class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(3, responseEntity.getBody().length);
	}


	//TODO: Zistit ako sa updatuje
	@Test
	@Order(6)
	void updateFirstBook() {
		BookEntity bookEntity = new BookEntity("Duma Key" , "Stephen King");
		bookEntity.setId(2);
		String url = "/books";
		restTemplate.put(url, bookEntity); // it just works
		//assertEquals("Duma Key", get by id);


	}

	//TODO: docker compose up --> docker service
	//jwt.io
	//invalidacia tokenov
	@Test
	@Order(7)
	void deleteFirstBook() {

		String url = "/books/1";

		restTemplate.delete(url);
		ResponseEntity<BookDto[]> responseEntity = restTemplate.getForEntity("/books", BookDto[].class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(2, responseEntity.getBody().length);
	}





*/

}
