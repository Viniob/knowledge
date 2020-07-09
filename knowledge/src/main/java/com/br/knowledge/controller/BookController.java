package com.br.knowledge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.br.knowledge.model.Book;
import com.br.knowledge.repository.BookRepository;

@RestController
public class BookController {

	@Autowired
	BookRepository bookRepository;

	@PreAuthorize("hasRole('admin')")
	@PostMapping("/createBook")
	public ResponseEntity<?> createBook(@RequestBody Book book) {
		try {
			return new ResponseEntity<Book>(bookRepository.save(book), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Book id existing at database", HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasRole('admin') or hasRole('user')")
	@GetMapping("/getBooks")
	public ResponseEntity<List<Book>> getBooks() {

		return new ResponseEntity<List<Book>>(bookRepository.findAll(), HttpStatus.OK);
	}

}
