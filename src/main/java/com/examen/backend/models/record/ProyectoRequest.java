package com.examen.backend.models.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProyectoRequest(

        @NotBlank
        @Size(max = 100)
        String nombre,

        String descripcion

) {
}