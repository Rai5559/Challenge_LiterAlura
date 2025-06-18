package com.rai69.literalura.repository;

import com.rai69.literalura.model.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<AuthorModel, Long> {
    Optional<AuthorModel> findByName(String name);
    Optional<AuthorModel> findByNameContainingIgnoreCase(String name);
}
