package com.mt.webnovelapi.repository;

import com.mt.webnovelapi.entity.Story;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoryRepository extends JpaRepository<Story, String> {
    boolean existsById(String id);
    boolean existsByTitle(String title);
    boolean existsBySlug(String slug);
    Optional<Story> findBySlug(String slug);
    Page<Story> findAll(Pageable pageable);
}
