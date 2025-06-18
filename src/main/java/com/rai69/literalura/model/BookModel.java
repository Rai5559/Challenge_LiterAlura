package com.rai69.literalura.model;

import jakarta.persistence.*;

@Entity
@Table
public class BookModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String title;
    private String language;
    private int downloadCount;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "author_id")
    private AuthorModel author;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public AuthorModel getAuthor() {
        return author;
    }

    public void setAuthor(AuthorModel author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "\n" + "----------- + Libro + ----------- \n" +
                "Título: " + title +
                (language != null ? " \n Idioma: " + language : "") +
                (downloadCount > 0 ? " \n Descargas: " + downloadCount : "") +
                 (author != null ? author.toString() : "")+
                "\n" + "----------- + Libro + ----------- ";
    }
}
