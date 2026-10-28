package com.examen.backend.controllers;

import com.examen.backend.models.record.AuthResponse;
import com.examen.backend.models.record.LoginRequest;
import com.examen.backend.models.record.RegistroRequest;
import com.examen.backend.services.usuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class usuariosController {

    private final usuarioService usuariosService;

    public usuariosController(usuarioService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @PostMapping("/registro")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse registrar(
            @Valid @RequestBody RegistroRequest request
    ) {
        return usuariosService.registrar(request);
    }

    @PostMapping("/login")
    public AuthResponse login(
            @Valid @RequestBody LoginRequest request
    ) {
        return usuariosService.login(request);
    }
}