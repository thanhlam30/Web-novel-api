package com.mt.moontruyen.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoryUpdateRequest {
    private String title;
    private String description;
    private String image;
    private String authorId;
    private List<String> categoryIds;
    private int views;
    private Boolean isCompleted;
}
