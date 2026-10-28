package com.examen.backend.repositories;

import com.examen.backend.models.usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface usuariosRepository extends JpaRepository<usuarios, Long> {

    Optional<usuarios> findByUsername(String username);

    boolean existsByUsername(String username);
}