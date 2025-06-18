package com.rai69.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookDTO(
    String title,
    AuthorDTO author,
    String language,
    int download_count
) {
    @Override
    public String toString() {
        return  "\n" + " ----------- + Libro + ----------- " + "\n" + 
                "Titulo = " + title + '\n' +
                "Autor = " + (author != null ? author.toString() : "No autor") +
                '\n' +
                "Lenguaje = " + (language != null ? language : "N/I") +
                '\n' +
                "Descargas = " + download_count
                + "\n" + " ----------- + Libro + ----------- ";
    }
}
