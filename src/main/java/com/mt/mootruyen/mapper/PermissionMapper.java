package com.mt.mootruyen.mapper;


import com.mt.mootruyen.dto.request.PermissionRequest;
import com.mt.mootruyen.dto.response.PermissionResponse;
import com.mt.mootruyen.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
