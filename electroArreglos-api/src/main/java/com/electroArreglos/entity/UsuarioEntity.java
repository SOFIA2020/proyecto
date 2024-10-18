package com.electroArreglos.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "usuario")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "email")
    private String email;

    @Column (name = "avatar")
    private String avatar;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false) // Relación n a 1 con Rol
    private RolEntity rol;

    public UsuarioEntity() {
    }

    public UsuarioEntity mapToUsuarioEntity(String usuarioNombre) {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNombre(usuarioNombre);
        return usuario;
    }


}
