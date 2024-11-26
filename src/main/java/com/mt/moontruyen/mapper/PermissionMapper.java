package com.mt.moontruyen.mapper;


import com.mt.moontruyen.dto.request.PermissionRequest;
import com.mt.moontruyen.dto.response.PermissionResponse;
import com.mt.moontruyen.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
