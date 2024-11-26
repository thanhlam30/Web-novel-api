package com.mt.moontruyen.controller;


import com.mt.moontruyen.dto.response.ApiResponse;
import com.mt.moontruyen.dto.request.AuthorCreationRequest;
import com.mt.moontruyen.dto.request.AuthorUpdateRequest;
import com.mt.moontruyen.entity.Author;
import com.mt.moontruyen.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    ApiResponse<List<Author>> getAllAuthors() {
        return ApiResponse.<List<Author>>builder()
                .result(authorService.getAllAuthors())
                .build();
    }

    @GetMapping("/id/{authorId}")
    ApiResponse<Author> getAuthorById(@PathVariable("authorId") String authorId) {
        return ApiResponse.<Author>builder()
                .result(authorService.getAuthorById(authorId))
                .build();
    }

    @GetMapping("{slug}")
    ApiResponse<Author> getAuthorBySlug(@PathVariable("slug") String slug) {
        return ApiResponse.<Author>builder()
                .result(authorService.getAuthorBySlug(slug))
                .build();
    }

    @PostMapping
    ApiResponse<Author> createAuthor(@RequestBody AuthorCreationRequest request) {
        return ApiResponse.<Author>builder()
                .result(authorService.createAuthor(request))
                .build();
    }

    @PutMapping("{authorId}")
    ApiResponse<Author> updateAuthor(@RequestBody AuthorUpdateRequest request, @PathVariable("authorId") String authorId) {
        return ApiResponse.<Author>builder()
                .result(authorService.updateAuthor(authorId, request))
                .build();
    }

    @DeleteMapping("{authorId}")
    ApiResponse<String> deleteAuthor(@PathVariable("authorId") String authorId) {
        authorService.deleteAuthor(authorId);
        return ApiResponse.<String>builder()
                .message("Author deleted successfully!")
                .build();
    }
}
