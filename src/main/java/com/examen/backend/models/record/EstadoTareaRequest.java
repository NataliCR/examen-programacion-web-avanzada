package com.examen.backend.models.record;
import com.examen.backend.models.enums.EstadoTarea;
import jakarta.validation.constraints.NotNull;

public record EstadoTareaRequest( @NotNull
                                  EstadoTarea estado) {
}
