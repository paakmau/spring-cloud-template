package com.example.demo.book.repo;

import com.example.demo.book.entity.Book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepo extends JpaRepository<Book, Long> {
    List<Book> findByTitle(String title);
}
