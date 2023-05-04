package com.example.demo.book.service;

import com.example.demo.book.dto.BookDto;
import com.example.demo.book.entity.Book;
import com.example.demo.book.mapper.BookMapper;
import com.example.demo.book.repo.BookRepo;
import com.example.demo.common.exception.NotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final BookRepo repo;

    public BookService(BookRepo repo) {
        this.repo = repo;
    }

    public BookDto create(BookDto dto) {
        return BookMapper.INSTANCE.toDto(repo.save(BookMapper.INSTANCE.toEntity(dto)));
    }

    public void delete(long id) {
        if (repo.findById(id).isEmpty()) {
            logger.warn("Can't delete Book by Id {}", id);
            throw new NotFoundException(Book.class, new String[] {"Id"});
        }
        repo.deleteById(id);
    }

    public BookDto update(long id, BookDto dto) {
        if (repo.findById(id).isEmpty()) {
            logger.warn("Can't update Book by Id {}", id);
            throw new NotFoundException(Book.class, new String[] {"Id"});
        }
        dto.withId(id);
        return BookMapper.INSTANCE.toDto(repo.save(BookMapper.INSTANCE.toEntity(dto)));
    }

    public BookDto get(long id) {
        Optional<Book> book = repo.findById(id);
        if (book.isEmpty()) {
            logger.warn("Can't get Book by Id {}", id);
            throw new NotFoundException(Book.class, new String[] {"Id"});
        }
        return BookMapper.INSTANCE.toDto(book.get());
    }

    public List<BookDto> getByTitle(String title) {
        return repo.findByTitle(title).stream().map(BookMapper.INSTANCE::toDto).toList();
    }
}
