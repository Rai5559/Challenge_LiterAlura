package com.rai69.literalura.model;

import jakarta.persistence.*;

@Entity
@Table
public class AuthorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String birthDate;
    private String deathDate;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(String deathDate) {
        this.deathDate = deathDate;
    }

    @Override
    public String toString() {
        return "\n" + "----------- + Autor + ----------- \n" +
                "Autor: " + name +
               (birthDate != null ? " \nNacimiento: " + birthDate : "") +
               (deathDate != null ? " \nFallecimiento: " + deathDate : "") +
               "\n"+ "----------- + Autor + ----------- ";
    }
}
