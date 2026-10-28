package com.examen.backend.services;

import com.examen.backend.models.record.AuthResponse;
import com.examen.backend.models.record.LoginRequest;
import com.examen.backend.models.record.RegistroRequest;
import com.examen.backend.models.usuarios;
import com.examen.backend.repositories.usuariosRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class usuarioService {

    private final usuariosRepository usuariosRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public usuarioService(
            usuariosRepository usuariosRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.usuariosRepository = usuariosRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Transactional
    public AuthResponse registrar(RegistroRequest request) {

        if (usuariosRepository.existsByUsername(request.username())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "El username ya esta registrado"
            );
        }

        usuarios nuevoUsuario = new usuarios();
        nuevoUsuario.setUsername(request.username());
        nuevoUsuario.setPassword(
                passwordEncoder.encode(request.password())
        );

        usuariosRepository.save(nuevoUsuario);

        String token = jwtService.generateToken(
                nuevoUsuario.getUsername()
        );

        return new AuthResponse(token, "Bearer");
    }

    public AuthResponse login(LoginRequest request) {

        usuarios usuario = usuariosRepository
                .findByUsername(request.username())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Credenciales incorrectas"
                ));

        if (!passwordEncoder.matches(
                request.password(),
                usuario.getPassword()
        )) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Credenciales incorrectas"
            );
        }

        String token = jwtService.generateToken(
                usuario.getUsername()
        );

        return new AuthResponse(token, "Bearer");
    }
}