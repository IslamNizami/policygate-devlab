package com.islamnizami.policygatedevlab.controller;

import com.islamnizami.policygatedevlab.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/{username}/roles/{roleName}")
    @PreAuthorize("hasAuthority('USER_ASSIGN')")
    public String assignRoleToUser(@PathVariable String username, @PathVariable String roleName) {
        userService.assignRoleToUser(username, roleName);
        return "Role " + roleName + " assigned to User " + username + " successfully!";
    }
}