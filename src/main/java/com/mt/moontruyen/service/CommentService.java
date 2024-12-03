package com.mt.moontruyen.service;

import com.mt.moontruyen.dto.request.CommentChapterCreationRequest;
import com.mt.moontruyen.dto.request.CommentStoryCreationRequest;
import com.mt.moontruyen.dto.response.CommentResponse;
import com.mt.moontruyen.entity.Chapter;
import com.mt.moontruyen.entity.Comment;
import com.mt.moontruyen.entity.Story;
import com.mt.moontruyen.entity.User;
import com.mt.moontruyen.exception.AppException;
import com.mt.moontruyen.exception.ErrorCode;
import com.mt.moontruyen.mapper.CommentMapper;
import com.mt.moontruyen.repository.ChapterRepository;
import com.mt.moontruyen.repository.CommentRepository;
import com.mt.moontruyen.repository.StoryRepository;
import com.mt.moontruyen.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentService {

    UserRepository userRepository;

    CommentRepository commentRepository;

    ChapterRepository chapterRepository;

    StoryRepository storyRepository;

    CommentMapper commentMapper;

    public List<CommentResponse> getChapterComments(String chapterId){
        Chapter chapter = chapterRepository.findById(chapterId).orElseThrow(() -> new AppException(ErrorCode.CHAPTER_NOT_FOUND));
        return commentRepository.findCommentsByChapterId(chapter.getId()).stream().map(commentMapper::toCommentResponse).toList();
    }

    public List<CommentResponse> getStoryComments(String storyId){
        Story story = storyRepository.findById(storyId).orElseThrow(() -> new AppException(ErrorCode.STORY_NOT_FOUND));
        return commentRepository.findCommentsByStoryId(story.getId()).stream().map(commentMapper::toCommentResponse).toList();
    }

    public Comment createCommentChapter(CommentChapterCreationRequest request){
        var context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        User user  = userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        Chapter chapter = chapterRepository.findById(request.getChapterId()).orElseThrow(() -> new AppException(ErrorCode.CHAPTER_NOT_FOUND));

        Comment parentComment = null;
        if (request.getParentCommentId() != null) {
            parentComment = commentRepository.findById(request.getParentCommentId()).orElseThrow(() -> new AppException(ErrorCode.COMMENT_NOT_FOUND));
        }

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setChapter(chapter);
        comment.setContent(request.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setParentComment(parentComment);

        return commentRepository.save(comment);
    }

    public Comment createCommentStory(CommentStoryCreationRequest request){
        var context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        User user  = userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        Story story = storyRepository.findById(request.getStoryId()).orElseThrow(() -> new AppException(ErrorCode.STORY_NOT_FOUND));

        Comment parentComment = null;
        if (request.getParentCommentId() != null) {
            parentComment = commentRepository.findById(request.getParentCommentId()).orElseThrow(() -> new AppException(ErrorCode.COMMENT_NOT_FOUND));
        }

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setStory(story);
        comment.setContent(request.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setParentComment(parentComment);

        return commentRepository.save(comment);
    }
}
