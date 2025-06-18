package com.rai69.literalura.util;

import com.rai69.literalura.dto.BookDTO;
import com.rai69.literalura.dto.AuthorDTO;
import com.rai69.literalura.model.BookModel;
import com.rai69.literalura.model.AuthorModel;

public class Mapper {
    public static BookModel toBookModel(BookDTO bookDTO) {
        BookModel book = new BookModel();
        book.setTitle(bookDTO.title());
        book.setLanguage(bookDTO.language() != null ? bookDTO.language() : "N/I");
        book.setDownloadCount(bookDTO.download_count());
        book.setAuthor(toAuthorModel(bookDTO.author()));
        return book;
    }

    public static AuthorModel toAuthorModel(AuthorDTO authorDTO) {
        if (authorDTO == null) return null;
        AuthorModel author = new AuthorModel();
        author.setName(authorDTO.name());
        author.setBirthDate(authorDTO.birthYear() != null ? authorDTO.birthYear().toString() : null);
        author.setDeathDate(authorDTO.deathYear() != null ? authorDTO.deathYear().toString() : null);
        return author;
    }
}