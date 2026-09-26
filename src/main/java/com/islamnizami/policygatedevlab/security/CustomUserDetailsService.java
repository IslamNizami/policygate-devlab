package com.islamnizami.policygatedevlab.security;

import com.islamnizami.policygatedevlab.model.entity.Permission;
import com.islamnizami.policygatedevlab.model.entity.Role;
import com.islamnizami.policygatedevlab.model.entity.User;
import com.islamnizami.policygatedevlab.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "user_security_details",key = "#username")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //Find user
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));


        //Create Authorities set for Spring security
        Set<GrantedAuthority> authorities = new HashSet<>();

        //add User's rolles and permissions
        for(Role role : user.getRoles()){
            authorities.add(new SimpleGrantedAuthority(role.getName()));

            for(Permission permission : role.getPermissions()){
                authorities.add(new SimpleGrantedAuthority(permission.getName()));
            }
        }

        //Return object for spring security

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                true, // account non expired
                true, //credentialsnonexpired
                true, // accountnonocked
                authorities
        );
    }
}
