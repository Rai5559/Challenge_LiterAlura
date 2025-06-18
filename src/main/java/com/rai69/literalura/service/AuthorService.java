package com.rai69.literalura.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rai69.literalura.dto.AuthorDTO;
import com.rai69.literalura.model.AuthorModel;
import com.rai69.literalura.repository.AuthorRepository;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public AuthorModel getOrCreateAuthor(AuthorDTO authorDTO) {
        if (authorDTO == null) return null;
        return authorRepository.findByName(authorDTO.name())
                .orElseGet(() -> {
                    AuthorModel author = new AuthorModel();
                    author.setName(authorDTO.name());
                    author.setBirthDate(authorDTO.birthYear() != null ? authorDTO.birthYear().toString() : null);
                    author.setDeathDate(authorDTO.deathYear() != null ? authorDTO.deathYear().toString() : null);
                    return authorRepository.save(author);
                });
    }

    public Object getAuthor(String name) {
        return authorRepository.findByNameContainingIgnoreCase(name)
                .<Object>map(author -> author)
                .orElse("\n"+ "--------- + Error + ------- \n" +
                 "No se encontró el autor: " + name + 
                 "\n" + "--------- + Error + ------- \n");
    }

    public Object getAuthorByYear(String year) {
        try {
            int targetYear = Integer.parseInt(year);
            var autoresVivos = authorRepository.findAll().stream()
                .filter(author -> {
                    try {
                        int nacimiento = author.getBirthDate() != null ? Integer.parseInt(author.getBirthDate()) : Integer.MIN_VALUE;
                        int fallecimiento = author.getDeathDate() != null ? Integer.parseInt(author.getDeathDate()) : Integer.MAX_VALUE;
                        return nacimiento <= targetYear && targetYear <= fallecimiento;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                })
                .toList();
            if (autoresVivos.isEmpty()) {
                return "\n--------- + Error + ------- \n" +
                        "No se encontraron autores vivos en el año: " + year +
                        "\n--------- + Error + ------- \n";
            }
            StringBuilder sb = new StringBuilder();
            sb.append("\nAutores vivos en el año ").append(year).append(":\n");
            autoresVivos.forEach(a -> sb.append(a.toString()).append("\n"));
            return sb.toString();
        } catch (NumberFormatException e) {
            return "\n--------- + Error + ------- \n" +
                    "El año ingresado no es válido: " + year +
                    "\n--------- + Error + ------- \n";
        }
    }
}
