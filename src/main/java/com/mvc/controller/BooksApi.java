package com.mvc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mvc.models.Book;
import com.mvc.service.BookService;

@RestController
public class BooksApi {
	private final BookService bookService;
	
	public BooksApi(BookService service) {
		this.bookService = service;
	}
	
	@RequestMapping("/api/books")
	public List<Book> AllBooks() {
		return bookService.allBooks();
	}
	@RequestMapping(value="/api/books", method=RequestMethod.POST)
	public Book Create(@RequestParam(value="title") String title, 
			@RequestParam(value="description") String description,
			@RequestParam(value="language") String language,
			@RequestParam(value="numPages") Integer numPages) {
		return bookService.createBook(new Book(title, description, language, numPages));
	}
	@RequestMapping("/api/books/{id}")
	public Book Show(@PathVariable("id") Long id) {
		Book book = bookService.findBook(id);
		return book;
	}
	@RequestMapping(value="/api/books/{id}", method=RequestMethod.PUT)
	public Book Update(@PathVariable("id") Long id, 
			@RequestParam(value="title") String title, 
			@RequestParam(value="description") String description,
			@RequestParam(value="language") String language,
			@RequestParam(value="numPages") Integer numPages) {
		return bookService.updateBook(id, title, description, language, numPages);
	}
	@RequestMapping(value="/api/books/{id}", method=RequestMethod.DELETE)
	public void Delete(@PathVariable("id") Long id) {
		bookService.deleteBook(id);
	}
}
