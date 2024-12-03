package com.mt.webnovelapi.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoryResponse {
    private String id;
    private String title;
    private String description;
    private String image;
    private AuthorResponse author;
    private List<CategoryResponse> categories;
    private int views;
    private Boolean isCompleted;
    private String slug;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
