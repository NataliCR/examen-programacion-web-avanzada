package com.examen.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 20)
    private String rol = "USER";

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<proyectos> proyectos;

    public usuarios() {
    }

    public usuarios(String username, String password) {
        this.username = username;
        this.password = password;
        this.rol = "USER";
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public List<proyectos> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<proyectos> proyectos) {
        this.proyectos = proyectos;
    }
}