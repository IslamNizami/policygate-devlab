package com.islamnizami.policygatedevlab.service;


import com.islamnizami.policygatedevlab.exception.GlobalExceptionHandler;
import com.islamnizami.policygatedevlab.exception.ResourceAlreadyExistsException;
import com.islamnizami.policygatedevlab.exception.ResourceNotFoundException;
import com.islamnizami.policygatedevlab.model.entity.Role;
import com.islamnizami.policygatedevlab.model.entity.User;
import com.islamnizami.policygatedevlab.repository.PermissionRepository;
import com.islamnizami.policygatedevlab.repository.RoleRepository;
import com.islamnizami.policygatedevlab.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuditLogService auditLogService;

    @Transactional
    @CacheEvict(value = "user_security_details",key = "#username")
    public void assignRoleToUser(String username, String roleName){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new ResourceAlreadyExistsException("Role not found: " + roleName));

        user.getRoles().add(role);
        userRepository.save(user);

        auditLogService.logAction("ASSIGN_ROLE_" + roleName,"USER_"+username);

    }

}
