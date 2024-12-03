package com.mt.webnovelapi.mapper;

import com.mt.webnovelapi.dto.request.StoryCreationRequest;
import com.mt.webnovelapi.dto.request.StoryUpdateRequest;
import com.mt.webnovelapi.dto.response.AuthorResponse;
import com.mt.webnovelapi.dto.response.CategoryResponse;
import com.mt.webnovelapi.dto.response.StoryResponse;
import com.mt.webnovelapi.entity.Author;
import com.mt.webnovelapi.entity.Category;
import com.mt.webnovelapi.entity.Story;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper(componentModel = "spring")
public interface StoryMapper {
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "categories", ignore = true)
    Story toStory(StoryCreationRequest request);

    @Mapping(target = "author", ignore = true)
    @Mapping(target = "categories", ignore = true)
    void toUpdateStory(StoryUpdateRequest request, @MappingTarget Story story);

    @Mapping(target = "author",expression = "java(mapAuthor(story.getAuthor()))")
    @Mapping(target = "categories", expression = "java(mapCategories(story.getCategories()))")
    StoryResponse toStoryResponse(Story story);

    default List<CategoryResponse> mapCategories(List<Category> categories) {
        return categories.stream()
                .map(category -> CategoryResponse.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .slug(category.getSlug())
                        .build())
                .toList();
    }
    default AuthorResponse mapAuthor(Author author) {
        return AuthorResponse.builder()
                .id(author.getId())
                .name(author.getName())
                .slug(author.getSlug())
                .build();
    }
}
