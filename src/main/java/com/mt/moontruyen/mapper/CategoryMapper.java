package com.mt.moontruyen.mapper;


import com.mt.moontruyen.dto.request.CategoryCreationRequest;
import com.mt.moontruyen.dto.request.CategoryUdatingRequest;
import com.mt.moontruyen.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryCreationRequest request);

    void toUpdateCategory(CategoryUdatingRequest request, @MappingTarget Category category);
}
