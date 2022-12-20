package com.example.shop.service;

import com.example.shop.entity.Book;
import com.example.shop.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;
    public String add(Book book) {
        List<Book> bookList = bookRepository.findAllByNameAndGenre(book.getName(), book.getGenre());
        if (bookList.isEmpty()){
            bookRepository.save(book);
            return "save";
        }
        return "busy";
    }
}
