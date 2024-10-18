package com.electroArreglos.entity;

import com.electroArreglos.controller.EstadoController;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "estado")
public class EstadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "estado_reparacion")
    private String estadoReparacion;

    @Column(name = "fecha_ingreso")
    private Date fechaIngreso;

    @Column(name = "fecha_salida")
    private Date fechaSalida;

    @ManyToOne
    @JoinColumn(name = "id_electrodomestico", nullable = false, updatable = true)
    private ElectrodomesticoEntity type;
}
