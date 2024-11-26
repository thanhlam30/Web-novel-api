package com.mt.moontruyen.repository;

import com.mt.moontruyen.entity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, String> {
    boolean existsByChapterNumber(int chapterNumber);
    Optional<Chapter> findByChapterNumber(int chapterNumber);
    Optional<Chapter> findBySlug(String slug);
    List<Chapter> findByStoryId(String storyId);
    List<Chapter> findByStorySlug(String slug);
}
