package com.mt.moontruyen.dto.response;

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
public class CommentResponse {
    private String id;
    private String username;
    private String content;
    private String parentCommentId;
    private List<CommentResponse> replies;
    private LocalDateTime createdAt;
}
