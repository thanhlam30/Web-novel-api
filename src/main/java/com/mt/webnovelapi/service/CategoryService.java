package com.mt.webnovelapi.service;


import com.mt.webnovelapi.dto.request.CategoryCreationRequest;
import com.mt.webnovelapi.dto.request.CategoryUpdatingRequest;
import com.mt.webnovelapi.entity.Category;
import com.mt.webnovelapi.exception.AppException;
import com.mt.webnovelapi.exception.ErrorCode;
import com.mt.webnovelapi.mapper.CategoryMapper;
import com.mt.webnovelapi.repository.CategoryRepository;
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
