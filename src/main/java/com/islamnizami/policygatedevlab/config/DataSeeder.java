package com.islamnizami.policygatedevlab.config;

import com.islamnizami.policygatedevlab.model.entity.Role;
import com.islamnizami.policygatedevlab.model.entity.User;
import com.islamnizami.policygatedevlab.repository.RoleRepository;
import com.islamnizami.policygatedevlab.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;


@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataSeeder.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.seed.admin-username:admin}")
    private String adminUsername;

    @Value("${app.seed.admin-password}")
    private String adminPassword;

    @Value("${app.seed.user-username:user}")
    private String userUsername;

    @Value("${app.seed.user-password}")
    private String userPassword;

    @Override
    public void run(String... args) {
        seedUser(adminUsername, adminPassword, "ROLE_ADMIN");
        seedUser(userUsername, userPassword, "ROLE_USER");
    }

    private void seedUser(String username, String rawPassword, String roleName) {
        if (userRepository.findByUsername(username).isPresent()) {
            log.debug("Seed passed, '{}' already exists.", username);
            return;
        }

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new IllegalStateException(
                        roleName + " could not be found"));

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setEnabled(true);
        user.setRoles(new HashSet<>());
        user.getRoles().add(role);

        userRepository.save(user);
        log.info("Seed user created: {}", username);
    }
}
