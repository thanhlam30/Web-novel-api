package com.mt.mootruyen.mapper;


import com.mt.mootruyen.dto.request.ChapterCreationRequest;
import com.mt.mootruyen.dto.request.ChapterUpdatingRequest;
import com.mt.mootruyen.entity.Chapter;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ChapterMapper {
    Chapter toChapter(ChapterCreationRequest request);
}
