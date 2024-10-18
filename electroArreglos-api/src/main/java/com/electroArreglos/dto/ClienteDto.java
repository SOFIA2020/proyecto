package com.electroArreglos.dto;

import com.electroArreglos.entity.UsuarioEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class ClienteDto {
    private Long id;

    @NotBlank
    private String nombre;

    @NotNull
    private Integer cedula;

    @NotBlank
    private String celular;

    @NotNull
    private String direccion;

    @NotNull
    private Date ingreso;

    private String usuarioEmail;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "usuario_id", nullable = false)

    private Long usuarioId;

    private String usuarioNombre;

}
