package com.mt.webnovelapi.mapper;


import com.mt.webnovelapi.dto.request.ChapterCreationRequest;
import com.mt.webnovelapi.entity.Chapter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChapterMapper {
    Chapter toChapter(ChapterCreationRequest request);
}
