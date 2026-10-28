package com.examen.backend.controllers;

import com.examen.backend.models.record.EstadoTareaRequest;
import com.examen.backend.models.record.TareaRequest;
import com.examen.backend.models.tareas;
import com.examen.backend.services.tareaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tareas")
public class tareasController {

    private final tareaService tareasService;

    public tareasController(tareaService tareasService) {
        this.tareasService = tareasService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public tareas crear(
            @Valid @RequestBody TareaRequest request,
            Authentication authentication
    ) {
        return tareasService.crear(
                request,
                authentication.getName()
        );
    }

    @PutMapping("/{id}")
    public tareas actualizarEstado(
            @PathVariable Long id,
            @Valid @RequestBody EstadoTareaRequest request,
            Authentication authentication
    ) {
        return tareasService.actualizarEstado(
                id,
                request,
                authentication.getName()
        );
    }
}