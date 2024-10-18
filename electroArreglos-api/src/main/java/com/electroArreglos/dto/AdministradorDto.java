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
public class AdministradorDto {

    private Long id;

    private String nombre;

    @NotNull
    private Integer cedula;

    @NotBlank
    private String celular;

    private String usuarioEmail;

    private Long usuarioId;

}
