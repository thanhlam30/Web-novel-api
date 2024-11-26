package com.mt.mootruyen.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorUpdateRequest {
    private String name;
    private String description;
    private String bio;
    private String avatar;
}
