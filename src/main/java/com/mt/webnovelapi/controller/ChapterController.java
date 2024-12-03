package com.mt.webnovelapi.controller;


import com.mt.webnovelapi.dto.response.ApiResponse;
import com.mt.webnovelapi.dto.request.ChapterCreationRequest;
import com.mt.webnovelapi.dto.request.ChapterUpdatingRequest;
import com.mt.webnovelapi.entity.Chapter;
import com.mt.webnovelapi.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chapters")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @GetMapping("/story/id/{storyId}")
    ApiResponse<List<Chapter>> getAllChaptersByStoryId(@PathVariable String storyId) {
        return ApiResponse.<List<Chapter>>builder()
                .result(chapterService.getAllChaptersByStoryId(storyId))
                .build();
    }

    @GetMapping("/story/{storySlug}")
    ApiResponse<List<Chapter>> getChapterBySlug(@PathVariable String storySlug) {
        return ApiResponse.<List<Chapter>>builder()
                .result(chapterService.getAllChaptersByStorySlug(storySlug))
                .build();
    }

    @GetMapping("/id/{chapterId}")
    ApiResponse<Chapter> getChapterById(@PathVariable String chapterId) {
        return ApiResponse.<Chapter>builder()
                .result(chapterService.getChapterById(chapterId))
                .build();
    }

    @PostMapping
    ApiResponse<Chapter> createChapter(@RequestBody ChapterCreationRequest request) {
        return ApiResponse.<Chapter>builder()
                .result(chapterService.createChapter(request))
                .build();
    }

    @PutMapping("{chapterId}")
    ApiResponse<Chapter> updateChapter(@RequestBody ChapterUpdatingRequest request, @PathVariable String chapterId) {
        return ApiResponse.<Chapter>builder()
                .result(chapterService.updateChapter(request, chapterId))
                .build();
    }

    @DeleteMapping("{chapterId}")
    ApiResponse<String> deleteChapter(@PathVariable String chapterId) {
        chapterService.deleteChapter(chapterId);
        return ApiResponse.<String>builder()
                .message("Story deleted successfully!")
                .build();
    }
}
