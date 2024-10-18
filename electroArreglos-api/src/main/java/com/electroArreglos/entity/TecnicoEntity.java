package com.electroArreglos.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "tecnico")
public class TecnicoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "cedula")
    private int cedula;

    @Column(name = "celular")
    private String celular;

    @Column(name = "titulo")
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false, updatable = true)
    private UsuarioEntity usuarioId;

    @ManyToOne
    @JoinColumn(name = "usuario_email", referencedColumnName = "email") // Relación n a 1 con email
    private UsuarioEntity ususarioEmail;

}
