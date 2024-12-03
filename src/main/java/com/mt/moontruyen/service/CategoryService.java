package com.mt.moontruyen.service;


import com.mt.moontruyen.dto.request.CategoryCreationRequest;
import com.mt.moontruyen.dto.request.CategoryUpdatingRequest;
import com.mt.moontruyen.entity.Category;
import com.mt.moontruyen.exception.AppException;
import com.mt.moontruyen.exception.ErrorCode;
import com.mt.moontruyen.mapper.CategoryMapper;
import com.mt.moontruyen.repository.CategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService {

    CategoryRepository categoryRepository;

    CategoryMapper categoryMapper;

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Category getCategoryById(String categoryId){
        return categoryRepository.findById(categoryId).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
    }

    public Category getCategoryBySlug(String slug){
        return categoryRepository.findBySlug(slug).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
    }

    public Category createCategory(CategoryCreationRequest request){
        if(categoryRepository.existsByName(request.getName())){
            throw new AppException(ErrorCode.CATEGORY_EXISTED);
        }
        Category category = categoryMapper.toCategory(request);
        return categoryRepository.save(category);
    }

    public Category updateCategory(CategoryUpdatingRequest request, String categoryId){
        Category category = getCategoryById(categoryId);
        categoryMapper.toUpdateCategory(request, category);
        return categoryRepository.save(category);
    }

    public void deleteCategory(String categoryId){
        categoryRepository.deleteById(categoryId);
    }
}
