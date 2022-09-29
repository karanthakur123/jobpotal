package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Book;


public interface BookRepository extends JpaRepository<Book,Long> {
	

	
   public List<Book> findBybookName(String Name);


}
