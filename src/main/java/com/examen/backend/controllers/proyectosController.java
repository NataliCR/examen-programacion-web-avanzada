package com.examen.backend.controllers;

import com.examen.backend.models.proyectos;
import com.examen.backend.models.record.ProyectoRequest;
import com.examen.backend.services.proyectoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proyectos")
public class proyectosController {

    private final proyectoService proyectosService;

    public proyectosController(proyectoService proyectosService) {
        this.proyectosService = proyectosService;
    }

    @GetMapping
    public List<proyectos> listar(Authentication authentication) {
        return proyectosService.listar(authentication.getName());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public proyectos crear(
            @Valid @RequestBody ProyectoRequest request,
            Authentication authentication
    ) {
        return proyectosService.crear(
                request,
                authentication.getName()
        );
    }
}