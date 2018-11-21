package com.mitrais.javabootcamp.librarymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mitrais.javabootcamp.librarymanagement.model.Book;

@Repository
public interface BookRepos extends JpaRepository<Book, Long>{

}
