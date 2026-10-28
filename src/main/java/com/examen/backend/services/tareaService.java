package com.examen.backend.services;

import com.examen.backend.models.enums.EstadoTarea;
import com.examen.backend.models.proyectos;
import com.examen.backend.models.record.EstadoTareaRequest;
import com.examen.backend.models.record.TareaRequest;
import com.examen.backend.models.tareas;
import com.examen.backend.models.usuarios;
import com.examen.backend.repositories.proyectosRepository;
import com.examen.backend.repositories.tareasRepository;
import com.examen.backend.repositories.usuariosRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class tareaService {

    private final tareasRepository tareasRepository;
    private final proyectosRepository proyectosRepository;
    private final usuariosRepository usuariosRepository;

    public tareaService(
            tareasRepository tareasRepository,
            proyectosRepository proyectosRepository,
            usuariosRepository usuariosRepository
    ) {
        this.tareasRepository = tareasRepository;
        this.proyectosRepository = proyectosRepository;
        this.usuariosRepository = usuariosRepository;
    }

    @Transactional
    public tareas crear(
            TareaRequest request,
            String username
    ) {
        usuarios usuario = buscarUsuario(username);

        proyectos proyecto = proyectosRepository
                .findById(request.proyectoId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Proyecto no encontrado"
                ));

        if (!proyecto.getUsuario().getId().equals(usuario.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "No puedes crear tareas en proyectos de otro usuario"
            );
        }

        tareas tarea = new tareas();

        tarea.setTitulo(request.titulo());

        tarea.setEstado(
                request.estado() == null
                        ? EstadoTarea.PENDIENTE
                        : request.estado()
        );

        tarea.setProyecto(proyecto);

        return tareasRepository.save(tarea);
    }

    @Transactional
    public tareas actualizarEstado(
            Long id,
            EstadoTareaRequest request,
            String username
    ) {
        usuarios usuario = buscarUsuario(username);

        tareas tarea = tareasRepository
                .findByIdAndProyecto_Usuario_Id(
                        id,
                        usuario.getId()
                )
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Tarea no encontrada"
                ));

        tarea.setEstado(request.estado());

        return tareasRepository.save(tarea);
    }

    private usuarios buscarUsuario(String username) {

        return usuariosRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuario no encontrado"
                ));
    }
}