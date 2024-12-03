package com.mt.webnovelapi.mapper;


import com.mt.webnovelapi.dto.request.CategoryCreationRequest;
import com.mt.webnovelapi.dto.request.CategoryUpdatingRequest;
import com.mt.webnovelapi.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryCreationRequest request);

    void toUpdateCategory(CategoryUpdatingRequest request, @MappingTarget Category category);
}
