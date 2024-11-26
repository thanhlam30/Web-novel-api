package com.mt.mootruyen.mapper;

import com.mt.mootruyen.dto.request.UserCreationRequest;
import com.mt.mootruyen.dto.request.UserUpdatingRequest;
import com.mt.mootruyen.dto.response.UserResponse;
import com.mt.mootruyen.entity.User;
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
