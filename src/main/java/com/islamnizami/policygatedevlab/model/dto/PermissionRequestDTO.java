package com.islamnizami.policygatedevlab.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PermissionRequestDTO {

    @NotBlank(message = "Permission can't be empty.")
    private String name;
}
