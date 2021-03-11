package com.java.springboot.library.librarysystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@EnableSwagger2
public class LibrarySystemApplication {
	private static final Logger LOG = LoggerFactory.getLogger(LibrarySystemApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LibrarySystemApplication.class, args);
		LOG.info("Library System App started");
	}

}
