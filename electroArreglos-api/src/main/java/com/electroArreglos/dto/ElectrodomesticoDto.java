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
public class ElectrodomesticoDto {
    private Long id;

    @NotBlank
    private String referencia;

    @NotBlank
    private String marca;

    @NotBlank
    private String serial;

    @NotNull
    private Date ingreso;

}
