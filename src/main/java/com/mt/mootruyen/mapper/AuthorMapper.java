package com.mt.mootruyen.mapper;

import com.mt.mootruyen.dto.request.AuthorCreationRequest;
import com.mt.mootruyen.dto.request.AuthorUpdateRequest;
import com.mt.mootruyen.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author toAuthor(AuthorCreationRequest request);

    Author toUpdateAuthor(AuthorUpdateRequest request, @MappingTarget Author author);
}
