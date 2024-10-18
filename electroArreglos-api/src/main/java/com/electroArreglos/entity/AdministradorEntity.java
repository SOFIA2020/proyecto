package com.electroArreglos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import java.util.List;
@Data
@Entity
@Table(name = "Administrador")
public class AdministradorEntity {
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

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private UsuarioEntity usuarioId;

    @ManyToOne
    @JoinColumn(name = "usuario_email", referencedColumnName = "email") // Relación n a 1 con email
    private UsuarioEntity ususarioEmail;

}



