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

import com.mitrais.javabootcamp.librarymanagement.exception.ResourceNotFoundException;
import com.mitrais.javabootcamp.librarymanagement.model.Book;
import com.mitrais.javabootcamp.librarymanagement.model.Shelf;
import com.mitrais.javabootcamp.librarymanagement.repository.BookRepos;
import com.mitrais.javabootcamp.librarymanagement.repository.ShelfRepos;

@RestController
@RequestMapping("/shelf")
public class LibraryController {
	
	@Autowired
	ShelfRepos shelfRepos;
	BookRepos bookRepos;
	
	// Get All
	@GetMapping("/getAll")
	public List<Shelf> getAllBooks(){
		return shelfRepos.findAll();
	}
	
	// Add new
	@PutMapping("/add/{id}")
	public Book addBook(@PathVariable(value="id") Long bookId) {
		// Update book status
		Book book = bookRepos.findById(bookId).orElseThrow(()->new ResourceNotFoundException("Book", "id", bookId));
		book.setStatus("shelved");		
		Book updatedBook = bookRepos.save(book);
		
		Shelf shelf;
		shelf.setCurrent_capacity(1);
		Shelf addShelf = shelfRepos.save(shelf);
		return updatedBook;
	}
	
	// Get shelf by ID
	@GetMapping("/getById/{id}")
	public Shelf getShelfById(@PathVariable(value="id") Long shelfId) {
		return shelfRepos.findById(shelfId).orElseThrow(()->new ResourceNotFoundException("Shelf", "id", shelfId));
	}
	
	// Remove
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Long shelfId){
		Shelf shelf = shelfRepos.findById(shelfId).orElseThrow(()->new ResourceNotFoundException("Shelf", "id", shelfId));
		
		shelfRepos.delete(shelf);
		return ResponseEntity.ok().build();
	}
	
}
