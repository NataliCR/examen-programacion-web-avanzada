package com.examen.backend.repositories;

import com.examen.backend.models.proyectos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface proyectosRepository extends JpaRepository<proyectos, Long> {

    List<proyectos> findByUsuario_Id(Long usuarioId);

    long countByUsuario_Id(Long usuarioId);
}