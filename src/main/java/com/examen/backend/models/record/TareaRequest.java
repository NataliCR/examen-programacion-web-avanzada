package com.examen.backend.models.record;
import com.examen.backend.models.enums.EstadoTarea;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TareaRequest(@NotBlank
                           @Size(max = 100)
                           String titulo,

                           EstadoTarea estado,

                           @NotNull
                           Long proyectoId) {
}
