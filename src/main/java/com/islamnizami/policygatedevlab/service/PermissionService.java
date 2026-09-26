package com.islamnizami.policygatedevlab.service;


import com.islamnizami.policygatedevlab.model.entity.Permission;
import com.islamnizami.policygatedevlab.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionService {
    private final PermissionRepository permissionRepository;

    @Transactional
    public Permission createPermission(String name){
        //Check if permission exist already or not, throw error if it is
        if(permissionRepository.findByName(name).isPresent()){
            throw new RuntimeException("Permission already exists: " + name);
        }
        Permission permission = new Permission();
        permission.setName(name);
        return permissionRepository.save(permission);
    }

    public List<Permission> getAllPermissions(){
        return permissionRepository.findAll();
    }
}
