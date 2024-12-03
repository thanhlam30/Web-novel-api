package com.mt.moontruyen.controller;

import com.mt.moontruyen.dto.request.CommentChapterCreationRequest;
import com.mt.moontruyen.dto.request.CommentStoryCreationRequest;
import com.mt.moontruyen.dto.response.ApiResponse;
import com.mt.moontruyen.dto.response.CommentResponse;
import com.mt.moontruyen.entity.Comment;
import com.mt.moontruyen.service.CommentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentController {

    CommentService commentService;

    @GetMapping("/chapter/{chapterId}")
    ApiResponse<List<CommentResponse>> getChapterComments(@PathVariable String chapterId) {
        return ApiResponse.<List<CommentResponse>>builder()
                .result(commentService.getChapterComments(chapterId))
                .build();
    }

    @GetMapping("/story/{storyId}")
    ApiResponse<List<CommentResponse>> getStoryComments(@PathVariable String storyId) {
        return ApiResponse.<List<CommentResponse>>builder()
                .result(commentService.getStoryComments(storyId))
                .build();
    }

    @PostMapping("/chapter")
    ApiResponse<Comment> createCommentChapter(@RequestBody CommentChapterCreationRequest request) {
        return ApiResponse.<Comment>builder()
                .result(commentService.createCommentChapter(request))
                .build();
    }

    @PostMapping("/story")
    ApiResponse<Comment> createCommentStory(@RequestBody CommentStoryCreationRequest request) {
        return ApiResponse.<Comment>builder()
                .result(commentService.createCommentStory(request))
                .build();
    }
}
