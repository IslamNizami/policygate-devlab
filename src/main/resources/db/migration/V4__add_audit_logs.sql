CREATE TABLE permission_audit_logs (
                                       id BIGSERIAL PRIMARY KEY,
                                       performed_by VARCHAR(50) NOT NULL,
                                       action VARCHAR(255) NOT NULL,
                                       target_entity VARCHAR(100) NOT NULL,
                                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);