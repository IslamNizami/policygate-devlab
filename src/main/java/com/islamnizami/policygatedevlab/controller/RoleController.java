package com.islamnizami.policygatedevlab.controller;
import com.islamnizami.policygatedevlab.model.entity.Role;
import com.islamnizami.policygatedevlab.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_WRITE')")
    public Role createRole(@RequestParam String name) {
        return roleService.createRole(name);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_WRITE')")
    public Role updateRole(@PathVariable Long id, @RequestParam String newName) {
        return roleService.updateRole(id, newName);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_READ')")
    public List<Role> getRoles() {
        return roleService.getAllRoles();
    }

    @PostMapping("/{roleName}/permissions/{permissionName}")
    @PreAuthorize("hasAuthority('ROLE_ASSIGN')")
    public String assignPermissionToRole(@PathVariable String roleName, @PathVariable String permissionName) {
        roleService.assignPermissionToRole(roleName, permissionName);
        return "Permission " + permissionName + " assigned to Role " + roleName + " successfully!";
    }
}