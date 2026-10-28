package com.examen.backend.services;

import com.examen.backend.models.proyectos;
import com.examen.backend.models.record.ProyectoRequest;
import com.examen.backend.models.usuarios;
import com.examen.backend.repositories.proyectosRepository;
import com.examen.backend.repositories.usuariosRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class proyectoService {

    private final proyectosRepository proyectosRepository;
    private final usuariosRepository usuariosRepository;

    public proyectoService(
            proyectosRepository proyectosRepository,
            usuariosRepository usuariosRepository
    ) {
        this.proyectosRepository = proyectosRepository;
        this.usuariosRepository = usuariosRepository;
    }

    public List<proyectos> listar(String username) {

        usuarios usuario = buscarUsuario(username);

        return proyectosRepository.findByUsuario_Id(usuario.getId());
    }

    @Transactional
    public proyectos crear(
            ProyectoRequest request,
            String username
    ) {
        usuarios usuario = buscarUsuario(username);

        long cantidad = proyectosRepository.countByUsuario_Id(
                usuario.getId()
        );

        if (cantidad >= 3) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Has alcanzado el limite de 3 proyectos"
            );
        }

        proyectos proyecto = new proyectos();

        proyecto.setNombre(request.nombre());
        proyecto.setDescripcion(request.descripcion());
        proyecto.setUsuario(usuario);

        return proyectosRepository.save(proyecto);
    }

    private usuarios buscarUsuario(String username) {

        return usuariosRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuario no encontrado"
                ));
    }
}