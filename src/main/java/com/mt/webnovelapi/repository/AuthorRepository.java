package com.mt.webnovelapi.repository;

import com.mt.webnovelapi.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, String > {
    boolean existsByName(String name);
    Optional<Author> findBySlug(String slug);
}
