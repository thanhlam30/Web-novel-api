package com.mt.moontruyen.controller;


import com.mt.moontruyen.dto.response.ApiResponse;
import com.mt.moontruyen.dto.request.CategoryCreationRequest;
import com.mt.moontruyen.dto.request.CategoryUpdatingRequest;
import com.mt.moontruyen.entity.Category;
import com.mt.moontruyen.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    ApiResponse<List<Category>> getAllCategories() {
        return ApiResponse.<List<Category>>builder()
                .result(categoryService.getAllCategories())
                .build();
    }

    @GetMapping("/id/{categoryId}")
    ApiResponse<Category> getCategoryById(@PathVariable String categoryId) {
        return ApiResponse.<Category>builder()
                .result(categoryService.getCategoryById(categoryId))
                .build();
    }

    @GetMapping("/{slug}")
    ApiResponse<Category> getCategoryBySlug(@PathVariable String slug) {
        return ApiResponse.<Category>builder()
                .result(categoryService.getCategoryBySlug(slug))
                .build();
    }

    @PostMapping
    ApiResponse<Category> createCategory(@RequestBody CategoryCreationRequest request) {
        return ApiResponse.<Category>builder()
                .result(categoryService.createCategory(request))
                .build();
    }

    @PutMapping("/{categoryId}")
    ApiResponse<Category> updateCategory(@RequestBody CategoryUpdatingRequest request, @PathVariable("categoryId") String categoryId) {
        return ApiResponse.<Category>builder()
                .result(categoryService.updateCategory(request, categoryId))
                .build();
    }

    @DeleteMapping("/{categoryId}")
    ApiResponse<String> deleteCategory(@PathVariable("categoryId") String categoryId) {
        categoryService.deleteCategory(categoryId);
        return ApiResponse.<String>builder()
                .message("Category deleted successfully")
                .build();
    }
}
