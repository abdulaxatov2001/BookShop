package com.example.shop.repository;

import com.example.shop.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {

   // Users getByUserName(@RequestParam String userName);
    List<Book> findAllByNameAndGenre(String name, String genre);
}
