package com.mt.webnovelapi.controller;

import com.mt.webnovelapi.dto.response.ApiResponse;
import com.mt.webnovelapi.dto.request.StoryCreationRequest;
import com.mt.webnovelapi.dto.request.StoryUpdateRequest;
import com.mt.webnovelapi.dto.response.PageResponse;
import com.mt.webnovelapi.dto.response.StoryResponse;
import com.mt.webnovelapi.entity.Story;
import com.mt.webnovelapi.service.StoryService;
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
