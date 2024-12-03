package com.mt.webnovelapi.mapper;


import com.mt.webnovelapi.dto.request.PermissionRequest;
import com.mt.webnovelapi.dto.response.PermissionResponse;
import com.mt.webnovelapi.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
