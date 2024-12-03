package com.mt.webnovelapi.mapper;


import com.mt.webnovelapi.dto.request.RoleRequest;
import com.mt.webnovelapi.dto.response.RoleResponse;
import com.mt.webnovelapi.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
