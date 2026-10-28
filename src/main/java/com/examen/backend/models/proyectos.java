package com.examen.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "proyectos")
public class proyectos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonIgnore
    private usuarios usuario;

    @OneToMany(mappedBy = "proyecto")
    @JsonIgnore
    private List<tareas> tareas;

    public proyectos() {
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(usuarios usuario) {
        this.usuario = usuario;
    }

    public List<tareas> getTareas() {
        return tareas;
    }

    public void setTareas(List<tareas> tareas) {
        this.tareas = tareas;
    }
}