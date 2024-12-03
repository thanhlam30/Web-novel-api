package com.mt.webnovelapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentStoryCreationRequest {
    private String content;
    private String storyId;
    private String parentCommentId;
}
