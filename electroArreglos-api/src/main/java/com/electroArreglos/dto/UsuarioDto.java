package com.electroArreglos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UsuarioDto {

    private Long id;

    @NotBlank
    private String nombre;

    @NotBlank
    private String email;

    @NotBlank
    private String avatar;

    @NotNull
    private Long rolId;

    private String rolNombre;
}
