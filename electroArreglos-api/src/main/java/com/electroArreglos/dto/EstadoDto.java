package com.electroArreglos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
@AllArgsConstructor
@Builder
public class EstadoDto {
    private Long id;

    @NotBlank
    private String estadoReparacion;

    @NotNull
    private Date fechaIngreso;

    private Date fechaSalida;

}
