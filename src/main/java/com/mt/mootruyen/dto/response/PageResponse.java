package com.mt.mootruyen.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {
    private int currentPage;
    private long totalPages;
    private int pageSize;
    private long totalElements;


    @Builder.Default
    private List<T> data = Collections.emptyList();
}
