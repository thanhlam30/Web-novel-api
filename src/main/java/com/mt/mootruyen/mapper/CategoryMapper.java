package com.mt.mootruyen.mapper;


import com.mt.mootruyen.dto.request.CategoryCreationRequest;
import com.mt.mootruyen.dto.request.CategoryUdatingRequest;
import com.mt.mootruyen.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryCreationRequest request);

    Category toUpdateCategory(CategoryUdatingRequest request, @MappingTarget Category category);
}
