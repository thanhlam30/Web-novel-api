package com.mt.moontruyen.mapper;


import com.mt.moontruyen.dto.request.RoleRequest;
import com.mt.moontruyen.dto.response.RoleResponse;
import com.mt.moontruyen.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
