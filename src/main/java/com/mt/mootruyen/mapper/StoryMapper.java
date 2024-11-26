package com.mt.mootruyen.mapper;

import com.mt.mootruyen.dto.request.StoryCreationRequest;
import com.mt.mootruyen.dto.request.StoryUpdateRequest;
import com.mt.mootruyen.entity.Story;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;



@Mapper(componentModel = "spring")
public interface StoryMapper {
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "categories", ignore = true)
    Story toStory(StoryCreationRequest request);

    @Mapping(target = "author", ignore = true)
    @Mapping(target = "categories", ignore = true)
    void toUpdateStory(StoryUpdateRequest request, @MappingTarget Story story);
}
