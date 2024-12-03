package com.mt.moontruyen.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentChapterCreationRequest {
    private String content;
    private String chapterId;
    private String parentCommentId;
}
