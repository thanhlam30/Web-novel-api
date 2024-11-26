package com.mt.mootruyen.dto.request;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChapterCreationRequest {
    @Lob
    private String content;
    private int chapterNumber;
    private String storyId;
}
