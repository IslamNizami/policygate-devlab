package com.islamnizami.policygatedevlab.controller;


import com.islamnizami.policygatedevlab.model.entity.Permission;
import com.islamnizami.policygatedevlab.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/permissions")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;

    @PostMapping
    @PreAuthorize("hasAuthority('PERMISSION_WRITE')")
    public Permission createPermission(@RequestParam String name) {
        return permissionService.createPermission(name);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('PERMISSION_READ')")
    public List<Permission> getPermissions() {
        return permissionService.getAllPermissions();
    }
}