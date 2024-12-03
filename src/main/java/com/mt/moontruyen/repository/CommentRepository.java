package com.mt.moontruyen.repository;

import com.mt.moontruyen.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {
    List<Comment> findCommentsByChapterId(String chapterId);
    List<Comment> findCommentsByStoryId(String storyId);
}
