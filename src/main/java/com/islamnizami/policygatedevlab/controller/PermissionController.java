package com.islamnizami.policygatedevlab.controller;


import com.islamnizami.policygatedevlab.model.dto.PermissionRequestDTO;
import com.islamnizami.policygatedevlab.model.dto.PermissionResponseDTO;
import com.islamnizami.policygatedevlab.model.entity.Permission;
import com.islamnizami.policygatedevlab.service.PermissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/permissions")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;

    @PostMapping
    @PreAuthorize("hasAuthority('PERMISSION_WRITE')")
    @ResponseStatus(HttpStatus.CREATED)
    public PermissionResponseDTO createPermission(@RequestBody @Valid PermissionRequestDTO requestDTO) {

        Permission savedPermission = permissionService.createPermission(requestDTO.getName());

        PermissionResponseDTO responseDTO  = new PermissionResponseDTO();
        responseDTO.setId(savedPermission.getId());
        responseDTO.setName(savedPermission.getName());

        return responseDTO;

    }

    @GetMapping
    @PreAuthorize("hasAuthority('PERMISSION_READ')")
    public List<PermissionResponseDTO> getPermissions() {
        return permissionService.getAllPermissions().stream().map(permission -> {
            PermissionResponseDTO responseDTO = new PermissionResponseDTO();
            responseDTO.setId(permission.getId());
            responseDTO.setName(permission.getName());
            return responseDTO;
        }).collect(Collectors.toList());
    }
}