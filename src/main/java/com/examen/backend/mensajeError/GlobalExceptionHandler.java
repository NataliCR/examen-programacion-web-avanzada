package com.examen.backend.mensajeError;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> manejarError(
            ResponseStatusException exception) {

        HttpStatus estado = HttpStatus.valueOf(
                exception.getStatusCode().value()
        );

        Map<String, String> respuesta = Map.of(
                "mensaje",
                exception.getReason() != null
                        ? exception.getReason()
                        : "Ha ocurrido un error"
        );

        return ResponseEntity.status(estado).body(respuesta);
    }
}