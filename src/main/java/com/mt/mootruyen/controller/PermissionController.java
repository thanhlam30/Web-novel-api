package com.mt.mootruyen.controller;

import com.mt.mootruyen.dto.request.PermissionRequest;
import com.mt.mootruyen.dto.response.ApiResponse;
import com.mt.mootruyen.dto.response.PermissionResponse;
import com.mt.mootruyen.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {
    PermissionService permissionService;

    @PostMapping
    ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest request) {
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<PermissionResponse>> getAll() {
        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permissionService.getAll())
                .build();
    }

    @DeleteMapping("/{permission}")
    ApiResponse<String> delete(@PathVariable String permission) {
        permissionService.delete(permission);
        return ApiResponse.<String>builder()
                .message("Permission deleted!")
                .build();
    }
}
