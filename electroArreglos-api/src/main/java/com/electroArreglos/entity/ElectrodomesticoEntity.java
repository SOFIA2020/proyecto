package com.electroArreglos.entity;

import com.electroArreglos.controller.TecnicoController;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Electrodomestico") /* indica la tabla a la que hago referencia*/
public class ElectrodomesticoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") /* indica el dato de la tabla a la cual estoy referenciando*/
    private Long id;
    /* de aca en adelante debo relacionar todos las columnas que tengo en la tabla no debe faltar ninguno con las caracteristicas*/
    @Column(name = "referencia")
    private String referencia;

    @Column(name = "marca") /*si en BD esta como camelcase lo debo colocar aca igual*/
    private String marca; /* en esta linea no se coloca como camelcase*/

    @Column(name = "serial")
    private String serial;

    @Column(name = "ingreso")
    private Date ingreso;

}
