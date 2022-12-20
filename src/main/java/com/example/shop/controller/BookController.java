 package com.example.shop.controller;

import com.example.shop.entity.Book;
import com.example.shop.repository.BookRepository;
import com.example.shop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookService  bookService;
    @GetMapping()
    public String showBook(Model model){
        model.addAttribute("books",bookRepository.findAll());
        model.addAttribute("book",new Book());
        return "book";
    }
    @PostMapping("/add")
    public String addBook(Model model, Book book){
        model.addAttribute("book",new Book());
        String success=bookService.add(book);
        if (success.equals("busy")) return "exist";
        model.addAttribute("books",bookRepository.findAll());
        return "book";
    }

    @GetMapping("edite/{id}")
    public String editCategory(@PathVariable Integer id, Model model) {
        Book book1 = bookRepository.getById(id);
        model.addAttribute("book", book1);
       // model.addAttribute("books",bookRepository.findAll());
        return "bookEdite";
    }

    @PostMapping("edite/{id}")
    public String saveEditeCategory(Book book, @PathVariable Integer id, Model model) {
        Book book1 = bookRepository.getById(id);
        if (book1.getName().equals(book.getName()) && book1.getGenre().equals(book.getGenre())){
            return "exist";
        }
        //model.addAttribute("books",bookRepository.findAll());
        book1.setName(book.getName());
        book1.setGenre(book.getGenre());
        bookRepository.save(book1);
        return "redirect:/book";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        bookRepository.deleteById(id);
        return "redirect:/book";
    }
}
