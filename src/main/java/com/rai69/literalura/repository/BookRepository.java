package com.rai69.literalura.repository;

import com.rai69.literalura.model.BookModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookModel, Long> {
    //getallbooks
    List<BookModel> findAll();

}
