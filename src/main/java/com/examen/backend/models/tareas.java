package com.examen.backend.models;

import com.examen.backend.models.enums.EstadoTarea;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "tareas")
public class tareas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoTarea estado = EstadoTarea.PENDIENTE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proyecto_id", nullable = false)
    @JsonIgnore
    private proyectos proyecto;

    public tareas() {
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public EstadoTarea getEstado() {
        return estado;
    }

    public void setEstado(EstadoTarea estado) {
        this.estado = estado;
    }

    public proyectos getProyecto() {
        return proyecto;
    }

    public void setProyecto(proyectos proyecto) {
        this.proyecto = proyecto;
    }
}