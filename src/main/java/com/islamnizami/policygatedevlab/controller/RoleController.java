package com.islamnizami.policygatedevlab.controller;
import com.islamnizami.policygatedevlab.model.dto.RoleRequestDTO;
import com.islamnizami.policygatedevlab.model.dto.RoleResponseDTO;
import com.islamnizami.policygatedevlab.model.entity.Role;
import com.islamnizami.policygatedevlab.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_WRITE')")
    @ResponseStatus(HttpStatus.CREATED)
    public RoleResponseDTO createRole(@RequestBody @Valid RoleRequestDTO requestDTO) {
        Role savedRole = roleService.createRole(requestDTO.getName());

        RoleResponseDTO responseDTO = new RoleResponseDTO();
        responseDTO.setId(savedRole.getId());
        responseDTO.setName(savedRole.getName());


        return responseDTO;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_WRITE')")
    public Role updateRole(@PathVariable Long id, @RequestParam String newName) {
        return roleService.updateRole(id, newName);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_READ')")
    public List<RoleResponseDTO> getRoles() {
        return roleService.getAllRoles().stream().map(role -> {
            RoleResponseDTO responseDTO = new RoleResponseDTO();
            responseDTO.setId(role.getId());
            responseDTO.setName(role.getName());
            return responseDTO;
        }).collect(Collectors.toList());
    }

    @PostMapping("/{roleName}/permissions/{permissionName}")
    @PreAuthorize("hasAuthority('ROLE_ASSIGN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void assignPermissionToRole(@PathVariable String roleName, @PathVariable String permissionName) {
        roleService.assignPermissionToRole(roleName, permissionName);
    }
}