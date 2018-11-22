package com.mitrais.javabootcamp.librarymanagement.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitrais.javabootcamp.librarymanagement.repository.BookRepos;
import com.mitrais.javabootcamp.librarymanagement.exception.ResourceNotFoundException;
import com.mitrais.javabootcamp.librarymanagement.model.Book;
@RestController
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	BookRepos bookRepos;
	
	// Get All Books
	@GetMapping("/getAll")
	public List<Book> getAllBooks(){
		return bookRepos.findAll();
	}
	
	// Add new Book
	@PostMapping("/add")
	public Book addBook(@Valid @RequestBody Book book) {
		return bookRepos.save(book);
	}
	
	// Get book by ID
	@GetMapping("/getById/{id}")
	public Book getBookById(@PathVariable(value="id") Long bookId) {
		return bookRepos.findById(bookId).orElseThrow(()->new ResourceNotFoundException("Book", "id", bookId));
	}
	
	// Get book by status
	@GetMapping("/getBystatus/{sts}")
	public List<Book> getBookByStatus(@PathVariable(value="sts") String bookStatus) {
		return bookRepos.findByStatus(bookStatus);
	}
	
	// Get book by title and status
	@GetMapping("/getByTitleAndStatus/{title}/{status}")
	public List<Book> getBookByTitleAndStatus(@PathVariable(value="title") String bookTitle,@PathVariable(value="status") String bookStatus) {
		return bookRepos.findByTitleAndStatus(bookTitle, bookStatus);
	}
	
	// Update book
	@PutMapping("/update/{id}")
	public Book updateBook(@PathVariable(value="id") Long bookId, @Valid @RequestBody Book bookDetails) {
		Book book = bookRepos.findById(bookId).orElseThrow(()->new ResourceNotFoundException("Book", "id", bookId));
		book.setTitle(bookDetails.getTitle());
		book.setAuthor(bookDetails.getAuthor());
		book.setIsbn(bookDetails.getIsbn());
		book.setStatus(bookDetails.getStatus());
		
		Book updatedBook = bookRepos.save(book);
		return updatedBook;
	}
	
	// Delete a Book
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Long bookId){
		Book book = bookRepos.findById(bookId).orElseThrow(()->new ResourceNotFoundException("Book", "id", bookId));
		
		bookRepos.delete(book);
		return ResponseEntity.ok().build();
	}
	
}
