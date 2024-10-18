package com.electroArreglos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ServerResponseDto {

    private String message;

    private int status;
}
