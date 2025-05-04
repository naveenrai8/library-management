package com.nr.authservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record LoginRequestDTO(
        @NotBlank(message = "user name can't be blank")
        @Size(min = 3, max = 20, message = "user name should be between 3 and 20 characters")
        String userName,
        @NotBlank(message = "password can't be blank")
        @Size(min = 3, max = 8, message = "password should be between 3 and 8 characters")
        String password
) {
}
