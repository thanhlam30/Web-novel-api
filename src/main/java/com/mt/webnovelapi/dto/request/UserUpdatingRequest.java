package com.mt.webnovelapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdatingRequest {
    private String password;
    private String email;
    private String avatar;
    List<String> roles;
}
