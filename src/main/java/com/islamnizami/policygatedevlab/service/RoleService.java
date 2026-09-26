package com.islamnizami.policygatedevlab.service;


import com.islamnizami.policygatedevlab.exception.ResourceAlreadyExistsException;
import com.islamnizami.policygatedevlab.exception.ResourceNotFoundException;
import com.islamnizami.policygatedevlab.model.entity.Permission;
import com.islamnizami.policygatedevlab.model.entity.Role;
import com.islamnizami.policygatedevlab.repository.PermissionRepository;
import com.islamnizami.policygatedevlab.repository.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    @Transactional
    public Role createRole(String name) {
        String formattedName = formatRoleName(name);

        if (roleRepository.findByName(formattedName).isPresent()) {
            throw new ResourceAlreadyExistsException("Role already exists: " + formattedName);
        }

        Role role = new Role();
        role.setName(formattedName);

        return roleRepository.save(role);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Transactional
    public void assignPermissionToRole(String roleName, String permissionName) {
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found: " + roleName));

        Permission permission = permissionRepository.findByName(permissionName)
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found: " + permissionName));

        role.getPermissions().add(permission);
        roleRepository.save(role);
    }

    @Transactional
    public Role updateRole(Long id, String newName) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + id));

        String formattedName = formatRoleName(newName);

        if (!role.getName().equals(formattedName) && roleRepository.findByName(formattedName).isPresent()) {
            throw new ResourceAlreadyExistsException("Role already exists: " + formattedName);
        }

        role.setName(formattedName);
        return roleRepository.save(role);
    }

    private String formatRoleName(String name) {
        return name.startsWith("ROLE_") ? name : "ROLE_" + name;
    }
}
