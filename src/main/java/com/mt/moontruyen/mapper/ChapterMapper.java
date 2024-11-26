package com.mt.moontruyen.mapper;


import com.mt.moontruyen.dto.request.ChapterCreationRequest;
import com.mt.moontruyen.entity.Chapter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChapterMapper {
    Chapter toChapter(ChapterCreationRequest request);
}
