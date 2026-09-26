package com.islamnizami.policygatedevlab.repository;

import com.islamnizami.policygatedevlab.model.entity.PermissionAuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionAuditLogRepository extends JpaRepository<PermissionAuditLog,Long> {
}
