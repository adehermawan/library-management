package com.mitrais.javabootcamp.librarymanagement.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "shelf")
@EntityListeners(AuditingEntityListener.class)


public class Shelf implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long shelf_id;
	private int max_capacity;
	private int current_capacity;
	private String books;
	
	
	public Long getShelf_id() {
		return shelf_id;
	}
	public void setShelf_id(Long shelf_id) {
		this.shelf_id = shelf_id;
	}
	public int getMax_capacity() {
		return max_capacity;
	}
	public void setMax_capacity(int max_capacity) {
		this.max_capacity = max_capacity;
	}
	public int getCurrent_capacity() {
		return current_capacity;
	}
	public void setCurrent_capacity(int current_capacity) {
		this.current_capacity = current_capacity;
	}
	public String getBooks() {
		return books;
	}
	public void setBooks(String books) {
		this.books = books;
	}
	
	
}
