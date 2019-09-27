package com.subjectsbooks.app;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BooksSubjectsApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(BooksSubjectsApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.servlet.context-path", "/bookssubjectjpaboot"));
		app.run(args);
	}
}
