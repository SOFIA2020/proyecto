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
public class TecnicoDto {
    private Long id;

    @NotBlank
    private String nombre;

    @NotNull
    private Integer cedula;

    @NotBlank
    private String celular;

    @NotBlank
    private String titulo;

    private String usuarioEmail;

    private Long usuarioId;

}
