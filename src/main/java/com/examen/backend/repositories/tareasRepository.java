package com.examen.backend.repositories;

import com.examen.backend.models.tareas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface tareasRepository extends JpaRepository<tareas, Long> {

    Optional<tareas> findByIdAndProyecto_Usuario_Id(
            Long tareaId,
            Long usuarioId
    );
}