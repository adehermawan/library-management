package com.mitrais.javabootcamp.librarymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mitrais.javabootcamp.librarymanagement.model.Shelf;

@Repository
public interface ShelfRepos extends JpaRepository<Shelf, Long>{

}
