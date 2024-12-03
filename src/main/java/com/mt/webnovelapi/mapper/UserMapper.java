package com.mt.webnovelapi.mapper;

import com.mt.webnovelapi.dto.request.UserCreationRequest;
import com.mt.webnovelapi.dto.request.UserUpdatingRequest;
import com.mt.webnovelapi.dto.response.UserResponse;
import com.mt.webnovelapi.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);


    @Mapping(target = "roles", ignore = true)
    void toUpdateUser(UserUpdatingRequest request, @MappingTarget User user);

    UserResponse toUserResponse(User user);
}
