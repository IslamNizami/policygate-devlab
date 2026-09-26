package com.islamnizami.policygatedevlab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PolicygateDevlabApplication {

    public static void main(String[] args) {
        SpringApplication.run(PolicygateDevlabApplication.class, args);
        System.out.println(new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode("admin123"));

    }

}
