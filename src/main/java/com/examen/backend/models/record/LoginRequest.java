package com.examen.backend.models.record;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(@NotBlank
                           String username,

                           @NotBlank
                           String password) {
}
