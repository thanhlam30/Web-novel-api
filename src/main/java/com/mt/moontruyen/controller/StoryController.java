package com.mt.moontruyen.controller;

import com.mt.moontruyen.dto.response.ApiResponse;
import com.mt.moontruyen.dto.request.StoryCreationRequest;
import com.mt.moontruyen.dto.request.StoryUpdateRequest;
import com.mt.moontruyen.dto.response.PageResponse;
import com.mt.moontruyen.dto.response.StoryResponse;
import com.mt.moontruyen.entity.Story;
import com.mt.moontruyen.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stories")
public class StoryController {
    @Autowired
    private StoryService storyService;

    @GetMapping
    ApiResponse<PageResponse<StoryResponse>> getAllStories(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        return ApiResponse.<PageResponse<StoryResponse>>builder()
                .result(storyService.getAllStories(page,size))
                .build();
    }
    @GetMapping("/id/{storyId}")
    ApiResponse<Story> getStory(@PathVariable String storyId) {
        return ApiResponse.<Story>builder()
                .result(storyService.getStoryById(storyId))
                .build();
    }

    @GetMapping("{slug}")
    ApiResponse<Story> getStoryBySlug(@PathVariable String slug) {
        return ApiResponse.<Story>builder()
                .result(storyService.getStoryBySlug(slug))
                .build();
    }

    @PostMapping
    ApiResponse<Story> createStory(@RequestBody StoryCreationRequest request){
        return ApiResponse.<Story>builder()
                .result(storyService.createStory(request))
                .build();
    }

    @PutMapping("{storyId}")
    ApiResponse<Story> updateStory(@PathVariable String storyId, @RequestBody StoryUpdateRequest request){
        return ApiResponse.<Story>builder()
                .result(storyService.updateStory(storyId, request))
                .build();
    }

    @DeleteMapping("{storyId}")
    ApiResponse<String> deleteStory(@PathVariable("storyId") String storyId){
        storyService.deleteStory(storyId);
        return ApiResponse.<String>builder()
                .message("Story deleted successfully!")
                .build();
    }
}
