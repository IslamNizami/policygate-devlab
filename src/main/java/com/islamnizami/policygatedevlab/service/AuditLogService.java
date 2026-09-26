package com.islamnizami.policygatedevlab.service;

import com.islamnizami.policygatedevlab.model.entity.Permission;
import com.islamnizami.policygatedevlab.model.entity.PermissionAuditLog;
import com.islamnizami.policygatedevlab.repository.PermissionAuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuditLogService {

    private final PermissionAuditLogRepository auditLogRepository;

    public void logAction(String action, String targetEntity){
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();

        PermissionAuditLog log = new PermissionAuditLog();
        log.setPerformedBy(currentUser);
        log.setAction(action);
        log.setTargetEntity(targetEntity);

        auditLogRepository.save(log);
    }
}
