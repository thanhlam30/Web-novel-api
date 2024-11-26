package com.mt.mootruyen.mapper;


import com.mt.mootruyen.dto.request.RoleRequest;
import com.mt.mootruyen.dto.response.RoleResponse;
import com.mt.mootruyen.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
