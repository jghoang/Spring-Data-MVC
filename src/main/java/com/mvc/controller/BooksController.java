package com.mvc.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.mvc.models.Book;
import com.mvc.service.BookService;

@Controller
public class BooksController {
	private final BookService bookService;
	public BooksController(BookService service) {
		this.bookService = service;
	}
	@RequestMapping("/books")
	public String Index(Model model) {
		model.addAttribute("books",bookService.allBooks());
		return "/books/index.jsp";
	}
	@RequestMapping("/books/new")
	public String New(@ModelAttribute("book") Book book) {
		return "/books/new.jsp";
	}
	@RequestMapping("/books/{id}/edit")
	public String Edit(@PathVariable("id") Long id, Model model) {
		model.addAttribute("book", bookService.findBook(id));
		return "/books/edit.jsp";
	}
	@RequestMapping(value="/books/{id}", method=RequestMethod.PUT)
	public String Update(@Valid @ModelAttribute("book") Book book, BindingResult result) {
		if(result.hasErrors())
			return "/books/edit.jsp";
		bookService.updateBook(book);
		return "redirect:/books";
	}
	@RequestMapping(value="/books", method=RequestMethod.POST)
	public String Create(@Valid @ModelAttribute("book") Book book, BindingResult result) {
		if(result.hasErrors())
			return "/books/new.jsp";
		bookService.createBook(book);
		return "redirect:/books";
	}
	@RequestMapping("/books/{id}")
	public String Show(@PathVariable("id") Long id, Model model) {
		model.addAttribute("book", bookService.findBook(id));
		return "/books/show.jsp";
	}
	@RequestMapping(value="/books/{id}", method=RequestMethod.DELETE)
	public String Delete(@PathVariable("id") Long id) {
		bookService.deleteBook(id);
		return "redirect:/books";
	}
}
