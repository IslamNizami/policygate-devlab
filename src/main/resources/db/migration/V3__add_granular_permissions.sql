DELETE FROM permissions WHERE name = 'ROLE_MANAGE';

INSERT INTO permissions (name) VALUES
                                   ('PERMISSION_READ'), ('PERMISSION_WRITE'),
                                   ('ROLE_READ'), ('ROLE_WRITE'), ('ROLE_ASSIGN'),
                                   ('USER_ASSIGN')
    ON CONFLICT (name) DO NOTHING;

INSERT INTO role_permissions (role_id, permission_id)
SELECT r.id, p.id FROM roles r, permissions p
WHERE r.name = 'ROLE_ADMIN'
    ON CONFLICT DO NOTHING;