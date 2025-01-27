package com.mt.webnovelapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorCreationRequest {
    private String name;
    private String description;
    private String bio;
    private String avatar;
}
