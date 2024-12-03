package com.mt.moontruyen.mapper;


import com.mt.moontruyen.dto.request.CategoryCreationRequest;
import com.mt.moontruyen.dto.request.CategoryUpdatingRequest;
import com.mt.moontruyen.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryCreationRequest request);

    void toUpdateCategory(CategoryUpdatingRequest request, @MappingTarget Category category);
}
