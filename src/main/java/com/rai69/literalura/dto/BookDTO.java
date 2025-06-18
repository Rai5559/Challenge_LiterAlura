package com.rai69.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Book(
    String title,
    List<Author> authors,
    List<String> languages,
    int download_count
) {

    @Override
    public String toString() {
        return  "Titulo = " + title + '\n' +
                "Autor@(s) = " +
                authors.stream()
                        .map(Author::toString)
                        .reduce((a, b) -> a + " / " + b)
                        .orElse("No autores")
                +
                '\n' +
                "Lenguajes = " +
                languages.stream()
                        .reduce((a, b) -> a + " / " + b)
                        .orElse("No hay información")
                +
                '\n' +
                "Descargas = " + download_count;
    }
}
