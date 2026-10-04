package com.islamnizami.policygatedevlab.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoleRequestDTO {

    @NotBlank(message = "Role name can't be empty or null")
    private String name;
}
