package com.islamnizami.policygatedevlab.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "permission_audit_logs")
@Getter
@Setter
@NoArgsConstructor
public class PermissionAuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String performedBy;
    private String action;
    private String targetEntity;
    private LocalDateTime createdAt = LocalDateTime.now();

}
