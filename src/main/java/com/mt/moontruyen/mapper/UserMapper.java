package com.mt.moontruyen.mapper;

import com.mt.moontruyen.dto.request.UserCreationRequest;
import com.mt.moontruyen.dto.request.UserUpdatingRequest;
import com.mt.moontruyen.dto.response.UserResponse;
import com.mt.moontruyen.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    @Mapping(target = "username", ignore = true)
    User toUpdateUser(UserUpdatingRequest request, @MappingTarget User user);
    UserResponse toUserResponse(User user);
}
