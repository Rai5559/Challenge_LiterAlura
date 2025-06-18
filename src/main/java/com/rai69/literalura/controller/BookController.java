package com.rai69.literalura.controller;

import com.rai69.literalura.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/fetch-and-save")
    public String fetchAndSaveBook(@RequestParam String query, @RequestParam(defaultValue = "0") int index) {
        return bookService.fetchAndSaveBook(query, index);
    }

    @GetMapping("/all")
    public String getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/bylanguage")
    public String getBooksByLanguage(@RequestParam String language) {
        return bookService.getBooksByLanguage(language);
    }
}
