package com.rai69.literalura.service;

import com.rai69.literalura.util.ApiReq;
import com.rai69.literalura.model.BookModel;
import com.rai69.literalura.model.AuthorModel;
import com.rai69.literalura.dto.BookDTO;
import com.rai69.literalura.dto.AuthorDTO;
import com.rai69.literalura.repository.BookRepository;
import com.rai69.literalura.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    private final ApiReq apiReq = new ApiReq();

    @Autowired
    private AuthorService authorService;

    

    @Transactional
    public String fetchAndSaveBook(String query, int index) {
        try {
            BookDTO bookDTO = apiReq.getSingleBook(query, index);
            BookModel bookModel = getOrCreateBook(bookDTO);
            BookDTO resultDTO = toBookDTO(bookModel);
            return resultDTO.toString();
        } catch (RuntimeException e) {
            return "\n No se encontró el libro solicitado o hubo un error \n";
        }
    }

    private BookModel getOrCreateBook(BookDTO bookDTO) {
        Optional<BookModel> existingBook = bookRepository.findAll().stream()
            .filter(b -> b.getTitle().equalsIgnoreCase(bookDTO.title()))
            .findFirst();
        if (existingBook.isPresent()) {
            BookModel bookModel = existingBook.get();
            if (bookModel.getAuthor() != null) bookModel.getAuthor().getName(); // fuerza carga
            return bookModel;
        }
        BookModel bookModel = new BookModel();
        bookModel.setTitle(bookDTO.title());
        bookModel.setLanguage(bookDTO.language() != null ? bookDTO.language() : "N/I");
        bookModel.setDownloadCount(bookDTO.download_count());
        AuthorModel author = authorService.getOrCreateAuthor(bookDTO.author());
        bookModel.setAuthor(author);
        bookRepository.save(bookModel);
        return bookModel;
    }

    private BookDTO toBookDTO(BookModel bookModel) {
        AuthorModel author = bookModel.getAuthor();
        AuthorDTO authorDTO = (author != null)
            ? new AuthorDTO(
                author.getName(),
                author.getBirthDate() != null ? Integer.valueOf(author.getBirthDate()) : null,
                author.getDeathDate() != null ? Integer.valueOf(author.getDeathDate()) : null)
            : null;
        return new BookDTO(
            bookModel.getTitle(),
            authorDTO,
            bookModel.getLanguage(),
            bookModel.getDownloadCount()
        );
    }

    public String getAllBooks() {
        StringBuilder result = new StringBuilder();
        bookRepository.findAll().forEach(book -> {
            result.append(book.toString()).append("\n");
        });
        return result.length() > 0 ? result.toString() : "No hay libros disponibles.";
    }

    public String getBooksByLanguage(String language) {
        StringBuilder result = new StringBuilder();
        bookRepository.findAll().stream()
            .filter(book -> book.getLanguage() != null && book.getLanguage().equalsIgnoreCase(language))
            .forEach(book -> result.append(book.toString()).append("\n"));
        return result.length() > 0 ? result.toString() : "No hay libros disponibles en el idioma: " + language;
    }
}
