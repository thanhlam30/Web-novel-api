package com.mt.webnovelapi.mapper;

import com.mt.webnovelapi.dto.request.AuthorCreationRequest;
import com.mt.webnovelapi.dto.request.AuthorUpdateRequest;
import com.mt.webnovelapi.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author toAuthor(AuthorCreationRequest request);

    void toUpdateAuthor(AuthorUpdateRequest request, @MappingTarget Author author);
}
