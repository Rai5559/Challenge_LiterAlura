package com.rai69.literalura.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rai69.literalura.service.AuthorService;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping("/author")
    public Object getAuthor(@RequestParam String name) {
        return authorService.getAuthor(name);
    }

    @GetMapping("/authoryear")
    public Object getAuthorByYear(@RequestParam String year) {
        return authorService.getAuthorByYear(year);
    }
}
