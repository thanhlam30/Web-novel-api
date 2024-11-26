package com.mt.moontruyen.mapper;

import com.mt.moontruyen.dto.request.AuthorCreationRequest;
import com.mt.moontruyen.dto.request.AuthorUpdateRequest;
import com.mt.moontruyen.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author toAuthor(AuthorCreationRequest request);

    Author toUpdateAuthor(AuthorUpdateRequest request, @MappingTarget Author author);
}
