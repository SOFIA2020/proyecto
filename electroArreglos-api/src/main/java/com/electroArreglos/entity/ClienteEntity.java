package com.electroArreglos.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
@Data
@Entity
@Table(name = "cliente")
public class ClienteEntity {
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

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "ingreso")
    private Date ingreso;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuarioId;

    @ManyToOne
    @JoinColumn(name = "usuario_email", referencedColumnName = "email") // Relación n a 1 con email
    private UsuarioEntity ususarioEmail;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "usuario_nombre", nullable = false)
    private UsuarioEntity usuarioNombre;

}
